package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import com.jciterceros.locadoracarrosjpa.repositories.FabricanteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.ModeloRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModeloService {

    private ModeloRepository modeloRepository;
    private FabricanteRepository fabricanteRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository, FabricanteRepository fabricanteRepository) {
        this.modeloRepository = modeloRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    @Transactional(readOnly = true)
    public List<ModeloDTO> findAll() {
        Iterable<Modelo> modelos = modeloRepository.findAll();
        List<ModeloDTO> modeloDTOList = new ArrayList<>();
        modelos.forEach(modelo -> modeloDTOList.add(copyEntityToDto(modelo)));
        return modeloDTOList;
    }

    @Transactional(readOnly = true)
    public ModeloDTO findById(Long id) {
        Modelo modelo = getModelo(id);
        return copyEntityToDto(modelo);
    }

    @Transactional(readOnly = false)
    public ModeloDTO insert(ModeloDTO modelo) {

        // Verifica se o fabricante existe
        Fabricante fabricante = fabricanteRepository.findById(modelo.getId_fabricante())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Fabricante não encontrado"));

        // Verifica se já existe um modelo com o mesmo nome para esse fabricante
        boolean exists = modeloRepository.existsByNomeAndFabricanteId(modelo.getNome(), modelo.getId_fabricante());
        if (exists) {
            throw new ResourceNotFoundException("Modelo já existe para esse fabricante");
        }

        modelo.setNome_fabricante(fabricante.getNome());
        return copyEntityToDto(modeloRepository.save(copyDtoToEntity(modelo)));
    }

    // Update modelo
    @Transactional(readOnly = false)
    public ModeloDTO update(Long id, ModeloDTO modelo) {
        Fabricante fabricante = fabricanteRepository.findById(modelo.getId_fabricante())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Fabricante não encontrado"));

        Modelo entity = getModelo(id);

        entity.setNome(modelo.getNome());
        entity.setFabricante(fabricante);

        return copyEntityToDto(modeloRepository.save(entity));
    }

    // delete
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Modelo entity = getModelo(id);
        modeloRepository.deleteById(entity.getId());
    }

    private Modelo getModelo(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Modelo não encontrado"));
    }

    public Modelo copyDtoToEntity(ModeloDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setId(dto.getId_fabricante());
        fabricante.setNome(dto.getNome_fabricante());

        Modelo entity = new Modelo();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setFabricante(fabricante);
        return entity;
    }

    public ModeloDTO copyEntityToDto(Modelo entity) {
        ModeloDTO dto = new ModeloDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setId_fabricante(entity.getFabricante().getId());
        dto.setNome_fabricante(entity.getFabricante().getNome());
        return dto;
    }
}
