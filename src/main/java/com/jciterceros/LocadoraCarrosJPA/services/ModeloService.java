package com.jciterceros.LocadoraCarrosJPA.services;

import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import com.jciterceros.LocadoraCarrosJPA.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        }
        return modeloDTOList;
    }
}
