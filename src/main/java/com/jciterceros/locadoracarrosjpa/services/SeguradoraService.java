package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.SeguradoraDTO;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import com.jciterceros.locadoracarrosjpa.repositories.LocacaoRepository;
import com.jciterceros.locadoracarrosjpa.repositories.MunicipioRepository;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoraRepository;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoratelefoneRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class SeguradoraService {
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;

    private final SeguradoraRepository seguradoraRepository;
    private final MunicipioRepository municipioRepository;

    private final SeguradoratelefoneRepository seguradoratelefoneRepository;

    private final LocacaoRepository locacaoRepository;

    public SeguradoraService(ModelMapper mapper, SeguradoraRepository seguradoraRepository, MunicipioRepository municipioRepository, SeguradoratelefoneRepository seguradoratelefoneRepository, LocacaoRepository locacaoRepository) {
        this.mapper = mapper;
        this.seguradoraRepository = seguradoraRepository;
        this.municipioRepository = municipioRepository;
        this.seguradoratelefoneRepository = seguradoratelefoneRepository;
        this.locacaoRepository = locacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<SeguradoraDTO> findAll() {
        List<Seguradora> seguradoras = seguradoraRepository.findAll();
        if (seguradoras.isEmpty()) {
            throw new ResourceNotFoundException("Não existem seguradoras cadastradas");
        }

        configureMapper();
        return seguradoras.stream()
                .map(seguradora -> mapper.map(seguradora, SeguradoraDTO.class))
                .sorted(Comparator.comparing(SeguradoraDTO::getId))
                .toList();
    }

    @Transactional(readOnly = true)
    public SeguradoraDTO findById(Long id) {
        configureMapper();
        return seguradoraRepository.findById(id)
                .map(seguradora -> mapper.map(seguradora, SeguradoraDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID da Seguradora não encontrado"));
    }

    @Transactional(readOnly = false)
    public SeguradoraDTO insert(SeguradoraDTO seguradoraDTO) {
        Municipio municipio = municipioRepository.findById(seguradoraDTO.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));
        if (seguradoraRepository.existsByCnpj(seguradoraDTO.getCnpj())) {
            throw new DatabaseException("CNPJ já cadastrado");
        }

        configureMapper();
        Seguradora seguradora = mapper.map(seguradoraDTO, Seguradora.class);
        seguradora.setEstado(municipio.getEstado());
        seguradora.setMunicipio(municipio);
        return mapper.map(seguradoraRepository.save(seguradora), SeguradoraDTO.class);
    }

    @Transactional(readOnly = false)
    public SeguradoraDTO update(Long id, SeguradoraDTO seguradoraDTO) {
        Seguradora seguradora = seguradoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID da Seguradora não encontrado"));
        Municipio municipio = municipioRepository.findById(seguradoraDTO.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));
        if (seguradoraRepository.existsByCnpj(seguradoraDTO.getCnpj()) && !seguradora.getCnpj().equals(seguradoraDTO.getCnpj())) {
            throw new DatabaseException("CNPJ já cadastrado para outra seguradora");
        }
        configureMapper();
        seguradora = mapper.map(seguradoraDTO, Seguradora.class);
        seguradora.setId(id);
        seguradora.setEstado(municipio.getEstado());
        seguradora.setMunicipio(municipio);
        return mapper.map(seguradoraRepository.save(seguradora), SeguradoraDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Seguradora seguradora = seguradoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID da Seguradora não encontrado"));
        if(seguradoratelefoneRepository.existsBySeguradoraId(id)){
            throw new DatabaseException("Existem telefones cadastrados para esta seguradora");
        }

        if(locacaoRepository.existsBySeguradoraId(id)){
            throw new DatabaseException("Existem locações cadastradas para esta seguradora");
        }

        seguradoraRepository.delete(seguradora);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Seguradora, SeguradoraDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNome(source.getNome());
                map().setCnpj(source.getCnpj());
                map().setEmail(source.getEmail());
                map().setValor(source.getValor());
                map().setId_estado(source.getEstado().getId());
                map().setId_municipio(source.getMunicipio().getId());
                map().setEstado(source.getEstado());
                map().setMunicipio(source.getMunicipio());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
