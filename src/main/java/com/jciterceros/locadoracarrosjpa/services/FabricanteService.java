package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.FabricanteDTO;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import com.jciterceros.locadoracarrosjpa.repositories.FabricanteRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    private final ModelMapper mapper;
    private FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository, ModelMapper mapper) {
        this.fabricanteRepository = fabricanteRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<FabricanteDTO> findAll() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        if (fabricantes.isEmpty()) {
            throw new ResourceNotFoundException("Não há fabricantes cadastrados");
        }
        return fabricantes.stream()
                .map(fabricante -> mapper.map(fabricante, FabricanteDTO.class))
                .sorted(Comparator.comparing(FabricanteDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public FabricanteDTO findById(Long id) {
        return fabricanteRepository.findById(id)
                .map(fabricante-> mapper.map(fabricante, FabricanteDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID do Fabricante não encontrado"));
    }

    @Transactional(readOnly = false)
    public FabricanteDTO insert(FabricanteDTO fabricante) {
        Optional<Fabricante> entity = fabricanteRepository.findByNome(fabricante.getNome());
        if (entity.isPresent()) {
            throw new DatabaseException("Fabricante já existe");
        }
        return mapper.map(fabricanteRepository.save(mapper.map(fabricante, Fabricante.class)), FabricanteDTO.class);
    }

    @Transactional(readOnly = false)
    public FabricanteDTO update(Long id, FabricanteDTO fabricanteDTO) {
        Optional<Fabricante> entityById = fabricanteRepository.findById(id);
        if (entityById.isEmpty()) {
            throw new ResourceNotFoundException("ID do Fabricante não encontrado");
        }
        Optional<Fabricante> entity = fabricanteRepository.findByNome(fabricanteDTO.getNome());
        if (entity.isPresent()) {
            throw new DatabaseException("Fabricante já existe");
        }
//        Fabricante entity = fabricante.get();
//        entity.setNome(fabricanteDTO.getNome());
//        entity = fabricanteRepository.save(entity);
//        return copyEntityToDto(entity);
        //fabricanteDTO.setId(id);
        return mapper.map(fabricanteRepository.save(mapper.map(fabricanteDTO, Fabricante.class)), FabricanteDTO.class);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fabricante não encontrado");
        }
        try {
            fabricanteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível excluir um fabricante que possui carros");
        }
    }

    public Fabricante copyDtoToEntity(FabricanteDTO dto) {
        Fabricante entity = new Fabricante();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }

    public FabricanteDTO copyEntityToDto(Fabricante entity) {
        FabricanteDTO dto = new FabricanteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }
}
