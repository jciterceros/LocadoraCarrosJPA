package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.SeguradoratelefoneDTO;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import com.jciterceros.locadoracarrosjpa.entities.Seguradoratelefone;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoraRepository;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoratelefoneRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.MethodArgumentNotValidException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class SeguradoratelefoneService {

    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;
    private final SeguradoratelefoneRepository seguradoratelefoneRepository;

    private final SeguradoraRepository seguradoraRepository;

    @Autowired
    public SeguradoratelefoneService(ModelMapper mapper, SeguradoratelefoneRepository seguradoratelefoneRepository, SeguradoraRepository seguradoraRepository) {
        this.mapper = mapper;
        this.seguradoratelefoneRepository = seguradoratelefoneRepository;
        this.seguradoraRepository = seguradoraRepository;
        configureMapper();
    }

    @Transactional(readOnly = true)
    public List<SeguradoratelefoneDTO> findAll() {
        List<Seguradoratelefone> seguradoratelefones = seguradoratelefoneRepository.findAll();
        if (seguradoratelefones.isEmpty()) {
            throw new ResourceNotFoundException("Não existem seguradoras cadastradas");
        }

        return seguradoratelefones.stream()
                .map(seguradoratelefone -> mapper.map(seguradoratelefone, SeguradoratelefoneDTO.class))
                .sorted(Comparator.comparing(SeguradoratelefoneDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public SeguradoratelefoneDTO findById(Long id) {
        return seguradoratelefoneRepository.findById(id)
                .map(seguradoratelefone -> mapper.map(seguradoratelefone, SeguradoratelefoneDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID do Seguradoratelefone não encontrado"));
    }

    @Transactional(readOnly = false)
    public SeguradoratelefoneDTO insert(SeguradoratelefoneDTO seguradoratelefoneDTO) {
        // TODO: Implementar as validações para as outras entidades
        // Validação de entrada
        if (seguradoratelefoneDTO == null) {
            throw new ResourceNotFoundException("DTO de Seguradoratelefone não pode ser nulo");
        }

        // Verifica se o ID da seguradora é válido
        Long seguradoraId = seguradoratelefoneDTO.getId_seguradora();
        if (seguradoraId == null) {
            throw new ResourceNotFoundException("ID da Seguradora não pode ser nulo");
        }

        Seguradora seguradora = seguradoraRepository.findById(seguradoratelefoneDTO.getId_seguradora())
                .orElseThrow(() -> new DatabaseException("ID da Seguradora não encontrado"));

        Seguradoratelefone seguradoratelefone = new Seguradoratelefone();
        seguradoratelefone.setTelefone(seguradoratelefoneDTO.getTelefone());
        seguradoratelefone.setSeguradora(seguradora);

        seguradoratelefone = seguradoratelefoneRepository.save(seguradoratelefone);
        return mapper.map(seguradoratelefone, SeguradoratelefoneDTO.class);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Seguradoratelefone, SeguradoratelefoneDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTelefone(source.getTelefone());
                map().setId_seguradora(source.getSeguradora().getId());
                map().setSeguradora(source.getSeguradora());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
