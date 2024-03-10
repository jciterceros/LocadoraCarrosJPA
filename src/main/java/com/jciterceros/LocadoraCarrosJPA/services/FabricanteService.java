package com.jciterceros.LocadoraCarrosJPA.services;

import com.jciterceros.LocadoraCarrosJPA.dto.FabricanteDTO;
import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;
import com.jciterceros.LocadoraCarrosJPA.repositories.FabricanteRepository;
import com.jciterceros.LocadoraCarrosJPA.services.exceptions.DatabaseException;
import com.jciterceros.LocadoraCarrosJPA.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricanteService {
    @Autowired
    private FabricanteRepository fabricanteRepository;

    // find all fabricantes
    @Transactional(readOnly = true)
    public List<FabricanteDTO> findAll() {
        List<FabricanteDTO> fabricanteDTOList = new ArrayList<>();
        Iterable<Fabricante> fabricantes = fabricanteRepository.findAll();
        for (Fabricante fabricante : fabricantes) {
            fabricanteDTOList.add(new FabricanteDTO(fabricante));
        }
        return fabricanteDTOList;
    }

    // find fabricante by id
    @Transactional(readOnly = true)
    public FabricanteDTO findById(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID do Fabricante não encontrado");
        }
        Fabricante entity = fabricanteRepository.findById(id).get();
        FabricanteDTO dto = new FabricanteDTO(entity);
        return dto;
    }

    // insert fabricante
    @Transactional(readOnly = false)
    public FabricanteDTO insert(FabricanteDTO fabricanteDTO) {
        Fabricante entity = new Fabricante();
        copyDtoToEntity(fabricanteDTO, entity);
        entity = fabricanteRepository.save(entity);
        return new FabricanteDTO(entity);
    }

    // update fabricante
    @Transactional(readOnly = false)
    public FabricanteDTO update(Long id, FabricanteDTO fabricanteDTO) {
        try {
            Fabricante entity = fabricanteRepository.getReferenceById(id);//getOne(id);
            copyDtoToEntity(fabricanteDTO, entity);
            entity = fabricanteRepository.save(entity);
            return new FabricanteDTO(entity);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new ResourceNotFoundException("Id " + id + " não encontrado ");
        }
    }

    // delete fabricante
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fabricante não encontrado");
        }
        try {
            fabricanteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error: " + e.getMessage());
            throw new DatabaseException("Não é possível excluir um fabricante que possui carros");
        }
    }

    private void copyDtoToEntity(FabricanteDTO dto, Fabricante entity) {
        entity.setNome(dto.getNome());
    }
}
