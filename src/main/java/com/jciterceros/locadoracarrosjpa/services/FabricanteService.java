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

    public static final String ID_DO_FABRICANTE_NAO_ENCONTRADO = "ID do Fabricante não encontrado";
    public static final String JA_EXISTE_UM_FABRICANTE_COM_ESTE_NOME = "Já existe um Fabricante com este nome";
    public static final String NAO_E_POSSIVEL_EXCLUIR_UM_FABRICANTE_QUE_POSSUI_MODELOS_ASSOCIADOS = "Não é possível excluir um fabricante que possui modelos associados";
    public static final String NAO_EXISTEM_FABRICANTES_CADASTRADOS = "Não existem fabricantes cadastrados";
    private final ModelMapper mapper;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository, ModelMapper mapper) {
        this.fabricanteRepository = fabricanteRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<FabricanteDTO> findAll() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        if (fabricantes.isEmpty()) {
            throw new ResourceNotFoundException(NAO_EXISTEM_FABRICANTES_CADASTRADOS);
        }
        return fabricantes.stream()
                .map(fabricante -> mapper.map(fabricante, FabricanteDTO.class))
                .sorted(Comparator.comparing(FabricanteDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public FabricanteDTO findById(Long id) {
        return fabricanteRepository.findById(id)
                .map(fabricante-> mapper.map(fabricante, FabricanteDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_FABRICANTE_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = false)
    public FabricanteDTO insert(FabricanteDTO fabricanteDTO) {
        Optional<Fabricante> entity = fabricanteRepository.findByNome(fabricanteDTO.getNome());
        if (entity.isPresent()) {
            throw new DatabaseException(JA_EXISTE_UM_FABRICANTE_COM_ESTE_NOME);
        }
        return mapper.map(fabricanteRepository.save(mapper.map(fabricanteDTO, Fabricante.class)), FabricanteDTO.class);
    }

    @Transactional(readOnly = false)
    public FabricanteDTO update(Long id, FabricanteDTO fabricanteDTO) {
        Optional<Fabricante> findEntityById = fabricanteRepository.findById(id);
        if (findEntityById.isEmpty()) {
            throw new ResourceNotFoundException(ID_DO_FABRICANTE_NAO_ENCONTRADO);
        }

        Optional<Fabricante> findEntityWithSameName = fabricanteRepository.findByNome(fabricanteDTO.getNome());
        if (findEntityWithSameName.isPresent()) {
            throw new DatabaseException(JA_EXISTE_UM_FABRICANTE_COM_ESTE_NOME);
        }

        Fabricante entityToUpdate = findEntityById.get();
        entityToUpdate.setNome(fabricanteDTO.getNome());
        return mapper.map(fabricanteRepository.save(entityToUpdate), FabricanteDTO.class);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new ResourceNotFoundException(ID_DO_FABRICANTE_NAO_ENCONTRADO);
        }
        try {
            fabricanteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(NAO_E_POSSIVEL_EXCLUIR_UM_FABRICANTE_QUE_POSSUI_MODELOS_ASSOCIADOS);
        }
    }
}
