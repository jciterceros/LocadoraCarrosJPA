package com.jciterceros.LocadoraCarrosJPA.controllers;

import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.services.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

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

    // insert modelo
    @PostMapping()
    public ModeloDTO insert(@RequestBody ModeloDTO modeloDTO) {
        System.out.println(modeloDTO.toString());
        return modeloService.insert(modeloDTO);
    }
}
