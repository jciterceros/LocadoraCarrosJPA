package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.FabricanteDTO;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import com.jciterceros.locadoracarrosjpa.repositories.FabricanteRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FabricanteService {

    private FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    @Transactional(readOnly = true)
    public List<FabricanteDTO> findAll() {
        List<FabricanteDTO> fabricanteDTOList = new ArrayList<>();
        Iterable<Fabricante> fabricantes = fabricanteRepository.findAll();
        for (Fabricante fabricante : fabricantes) {
            fabricanteDTOList.add(copyEntityToDto(fabricante));
        }
        Collections.sort(fabricanteDTOList, Comparator.comparing(FabricanteDTO::getId));
        return fabricanteDTOList;
    }

    @Transactional(readOnly = true)
    public FabricanteDTO findById(Long id) {
        Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
        if (!fabricante.isPresent()) {
            throw new ResourceNotFoundException("ID do Fabricante não encontrado");
        }
        return copyEntityToDto(fabricante.get());
    }

    @Transactional(readOnly = false)
    public FabricanteDTO insert(FabricanteDTO fabricante) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findByNome(fabricante.getNome());
        if (fabricanteOptional.isPresent()) {
            throw new DatabaseException("Fabricante já existe");
        }
        return copyEntityToDto(fabricanteRepository.save(copyDtoToEntity(fabricante)));
    }

    @Transactional(readOnly = false)
    public FabricanteDTO update(Long id, FabricanteDTO fabricanteDTO) {
        Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
        if (!fabricante.isPresent()) {
            throw new ResourceNotFoundException("ID do Fabricante não encontrado");
        }
        Fabricante entity = fabricante.get();
        entity.setNome(fabricanteDTO.getNome());
        entity = fabricanteRepository.save(entity);
        return copyEntityToDto(entity);
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
