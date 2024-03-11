package com.jciterceros.LocadoraCarrosJPA.services;

import com.jciterceros.LocadoraCarrosJPA.dto.FabricanteDTO;
import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import com.jciterceros.LocadoraCarrosJPA.repositories.ModeloRepository;
import com.jciterceros.LocadoraCarrosJPA.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    // find all Modelos
    @Transactional(readOnly = true)
    public List<ModeloDTO> findAll() {
        List<ModeloDTO> modeloDTOList = new ArrayList<>();
        Iterable<Modelo> modelos = modeloRepository.findAll();
        for (Modelo modelo : modelos) {
            modeloDTOList.add(new ModeloDTO(modelo));
            System.out.println(modelo.toString());
        }
        return modeloDTOList;
    }
    // find all Modelos using Set
    @Transactional(readOnly = true)
    public Set<ModeloDTO> findAllSet() {
        Set<ModeloDTO> modeloDTOList = new HashSet<>();
        Iterable<Modelo> modelos = modeloRepository.findAll();
        for (Modelo modelo : modelos) {
            modeloDTOList.add(new ModeloDTO(modelo));
            System.out.println(modelo.toString());
        }
        return modeloDTOList;
    }

    // find modelo by id
    @Transactional(readOnly = true)
    public ModeloDTO findById(Long id) {
        if(!modeloRepository.existsById(id)){
            throw new ResourceNotFoundException("ID do Modelo não encontrado");
        }
        Modelo entity = modeloRepository.findById(id).get();
        ModeloDTO dto = new ModeloDTO(entity);
        return dto;
    }

    // insert modelo
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
        //entity.setFabricante(dto.getFabricante());
    }
}
