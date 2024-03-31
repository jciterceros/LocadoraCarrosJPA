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
import java.util.Optional;

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
            fabricanteDTOList.add(new FabricanteDTO(fabricante));
        }
        return fabricanteDTOList;
    }

    @Transactional(readOnly = true)
    public FabricanteDTO findById(Long id) {
        Optional<Fabricante> optionalFabricante = fabricanteRepository.findById(id);
        if (!optionalFabricante.isPresent()) {
            throw new ResourceNotFoundException("ID do Fabricante não encontrado");
        }
        Fabricante entity = optionalFabricante.get();
        return new FabricanteDTO(entity);
    }

    @Transactional(readOnly = false)
    public FabricanteDTO insert(FabricanteDTO fabricanteDTO) {
        Fabricante entity = new Fabricante();
        copyDtoToEntity(fabricanteDTO, entity);
        entity = fabricanteRepository.save(entity);
        return new FabricanteDTO(entity);
    }

    @Transactional(readOnly = false)
    public FabricanteDTO update(Long id, FabricanteDTO fabricanteDTO) {
        try {
            Fabricante entity = fabricanteRepository.getReferenceById(id);
            copyDtoToEntity(fabricanteDTO, entity);
            entity = fabricanteRepository.save(entity);
            return new FabricanteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado ");
        }
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

    private void copyDtoToEntity(FabricanteDTO dto, Fabricante entity) {
        entity.setNome(dto.getNome());
    }
}
