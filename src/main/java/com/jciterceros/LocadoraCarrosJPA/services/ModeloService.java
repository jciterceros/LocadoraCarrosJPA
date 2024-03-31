package com.jciterceros.LocadoraCarrosJPA.services;

import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import com.jciterceros.LocadoraCarrosJPA.repositories.ModeloRepository;
import com.jciterceros.LocadoraCarrosJPA.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ModeloService {

    private ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    @Transactional(readOnly = true)
    public List<ModeloDTO> findAll() {
        List<ModeloDTO> modeloDTOList = new ArrayList<>();
        Iterable<Modelo> modelos = modeloRepository.findAll();
        for (Modelo modelo : modelos) {
            modeloDTOList.add(new ModeloDTO(modelo));
        }
        return modeloDTOList;
    }

    @Transactional(readOnly = true)
    public Set<ModeloDTO> findAllSet() {
        Set<ModeloDTO> modeloDTOList = new HashSet<>();
        Iterable<Modelo> modelos = modeloRepository.findAll();
        for (Modelo modelo : modelos) {
            modeloDTOList.add(new ModeloDTO(modelo));
        }
        return modeloDTOList;
    }


    @Transactional(readOnly = true)
    public ModeloDTO findById(Long id) {
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        if(!optionalModelo.isPresent()){
            throw new ResourceNotFoundException("ID do Modelo não encontrado");
        }
        Modelo entity = optionalModelo.get();
        return  new ModeloDTO(entity);
    }


    @Transactional(readOnly = false)
    public ModeloDTO insert(ModeloDTO modeloDTO) {
        Modelo entity = new Modelo();
        copyDtoToEntity(modeloDTO, entity);
        entity = modeloRepository.save(entity);
        return new ModeloDTO(entity);
    }


    private void copyDtoToEntity(ModeloDTO dto, Modelo entity)
    {
        entity.setNome(dto.getNome());
    }
}
