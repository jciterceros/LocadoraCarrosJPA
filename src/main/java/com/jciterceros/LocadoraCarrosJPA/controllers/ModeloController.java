package com.jciterceros.LocadoraCarrosJPA.controllers;

import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.services.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController {

    private ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    // find all modelos
    @GetMapping()
    public List<ModeloDTO> findAll() {
        return modeloService.findAll();
    }

    // find modelo by id
    @GetMapping(value = "/{id}")
    public ModeloDTO findById(@PathVariable Long id) {
        return modeloService.findById(id);
    }
}
