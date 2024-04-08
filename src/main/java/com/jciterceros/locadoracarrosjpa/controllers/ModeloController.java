package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.services.ModeloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController {

    private final ModelMapper mapper;
    private final ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService, ModelMapper mapper) {
        this.modeloService = modeloService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<ModeloDTO>> findAll() {
        return ResponseEntity.ok().body(modeloService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModeloDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(modeloService.findById(id));
    }

    // Insert
    @PostMapping()
    public ResponseEntity<ModeloDTO> insert(@RequestBody ModeloDTO modeloDTO) {
        Long id = modeloService.insert(modeloDTO).getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(modeloService.findById(id));
    }

    // Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<ModeloDTO> update(@PathVariable Long id, @RequestBody ModeloDTO modeloDTO) {
        return ResponseEntity.ok().body(modeloService.update(id, modeloDTO));
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        modeloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
