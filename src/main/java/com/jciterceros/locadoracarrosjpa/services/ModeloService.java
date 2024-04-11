package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import com.jciterceros.locadoracarrosjpa.repositories.FabricanteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.ModeloRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ModeloService {

    public static final String ID_DO_MODELO_NAO_ENCONTRADO = "ID do Modelo não encontrado";
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;
    private final ModeloRepository modeloRepository;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository, FabricanteRepository fabricanteRepository, ModelMapper mapper) {
        this.modeloRepository = modeloRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.mapper = mapper;
    }


    @Transactional(readOnly = true)
    public List<ModeloDTO> findAll() {
        List<Modelo> modelos = modeloRepository.findAll();
        if (modelos.isEmpty()) {
            throw new ResourceNotFoundException("Não existem modelos cadastrados");
        }

        configureMapper();

        return modelos.stream()
                .map(modelo -> mapper.map(modelo, ModeloDTO.class))
                .sorted(Comparator.comparing(ModeloDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public ModeloDTO findById(Long id) {
        return modeloRepository.findById(id)
                .map(modelo -> mapper.map(modelo, ModeloDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = false)
    public ModeloDTO insert(ModeloDTO modeloDTO) {

        Fabricante fabricante = fabricanteRepository.findById(modeloDTO.getId_fabricante())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Fabricante não encontrado"));

        boolean exists = modeloRepository.existsByNomeAndFabricanteId(modeloDTO.getNome(), modeloDTO.getId_fabricante());
        if (exists) {
            throw new DatabaseException("Modelo já existe para esse fabricante");
        }
        configureMapper();

        Modelo entity = new Modelo();
        entity.setNome(modeloDTO.getNome());
        entity.setFabricante(fabricante);

        return mapper.map(modeloRepository.save(entity), ModeloDTO.class);
    }

    @Transactional(readOnly = false)
    public ModeloDTO update(Long id, ModeloDTO modeloDTO) {
        Fabricante fabricante = fabricanteRepository.findById(modeloDTO.getId_fabricante())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Fabricante não encontrado"));

        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO));

        boolean exists = modeloRepository.existsByNomeAndFabricanteId(modeloDTO.getNome(), modeloDTO.getId_fabricante());
        if (exists) {
                throw new DatabaseException("Modelo já existe para esse fabricante");
        }

        configureMapper();

        modelo.setNome(modeloDTO.getNome());
        modelo.setFabricante(fabricante);

        return mapper.map(modeloRepository.save(modelo), ModeloDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Modelo entity = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO));

        if(!entity.getCarro().isEmpty()){
            throw new DatabaseException("Não é possível excluir um modelo que possui carros associados");
        }
        modeloRepository.deleteById(entity.getId());
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Modelo, ModeloDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNome(source.getNome());
                map().setId_fabricante(source.getFabricante().getId());
                map().setNome_fabricante(source.getFabricante().getNome());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
