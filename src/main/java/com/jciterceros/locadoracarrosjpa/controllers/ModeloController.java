package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.services.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController {

    private ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping()
    public List<ModeloDTO> findAll() {
        return modeloService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ModeloDTO findById(@PathVariable Long id) {
        return modeloService.findById(id);
    }

    // Insert
    @PostMapping()
    public ModeloDTO insert(@RequestBody ModeloDTO modeloDTO) {
        return modeloService.insert(modeloDTO);
    }

    // Update
    @PutMapping(value = "/{id}")
    public ModeloDTO update(@PathVariable Long id, @RequestBody ModeloDTO modeloDTO) {
        return modeloService.update(id, modeloDTO);
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        modeloService.delete(id);
    }
}
