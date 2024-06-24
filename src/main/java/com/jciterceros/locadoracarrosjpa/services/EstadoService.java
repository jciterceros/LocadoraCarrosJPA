package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.EstadoDTO;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.repositories.EstadoRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository, ModelMapper mapper) {
        this.estadoRepository = estadoRepository;
        this.mapper = mapper;
        configureMapper();
    }

    @Transactional(readOnly = true)
    public List<EstadoDTO> findAll() {
        List<Estado> estados = estadoRepository.findAll();//searchAll();
        if (estados.isEmpty()) {
            throw new ResourceNotFoundException("Não existem estados cadastrados");
        }

        return estados.stream()
                .map(estado -> mapper.map(estado, EstadoDTO.class))
                .sorted(Comparator.comparing(EstadoDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public EstadoDTO findById(Long id) {
        return estadoRepository.searchById(id).stream()
                .map(estado -> mapper.map(estado, EstadoDTO.class))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("ID do Estado não encontrado"));
    }

    @Transactional(readOnly = false)
    public EstadoDTO insert(EstadoDTO estadoDTO) {
        if(estadoDTO.getSigla().length() != 2) {
            throw new DatabaseException("A sigla do estado deve ter 2 caracteres");
        }
        Optional<Estado> entity = estadoRepository.findByDescricao(estadoDTO.getDescricao());
        if (entity.isPresent()) {
            throw new DatabaseException("Já existe um Estado com esta descrição");
        }
        return mapper.map(estadoRepository.save(mapper.map(estadoDTO, Estado.class)), EstadoDTO.class);
    }

    @Transactional(readOnly = false)
    public EstadoDTO update(Long id, EstadoDTO estadoDTO) {
        if(estadoDTO.getSigla().length() != 2) {
            throw new DatabaseException("A sigla do estado deve ter 2 caracteres");
        }
        Estado entity = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Estado não encontrado"));

        entity.setDescricao(estadoDTO.getDescricao());
        entity.setSigla(estadoDTO.getSigla());
        return mapper.map(estadoRepository.save(entity), EstadoDTO.class);
    }
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Estado entity = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Estado não encontrado"));
        if(!entity.getEstadoMunicipios().isEmpty()) {
            throw new DatabaseException("Não é possível excluir um estado que possui municipios associados");
        }
        estadoRepository.deleteById(entity.getId());
    }

    public void configureMapper() {
        if (!Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Estado, EstadoDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDescricao(source.getDescricao());
                map().setSigla(source.getSigla());
                map().setEstadoMunicipios(source.getEstadoMunicipios());
                map().setEstadoClientes(source.getEstadoClientes());
                map().setEstadoSeguradoras(source.getEstadoSeguradoras());
            }
        });
        mapperAllreadyConfigured = true;
    }


}
