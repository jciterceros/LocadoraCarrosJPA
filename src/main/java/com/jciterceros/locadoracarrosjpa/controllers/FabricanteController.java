package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.FabricanteDTO;
import com.jciterceros.locadoracarrosjpa.services.FabricanteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteController {

    private final ModelMapper mapper;
    private final FabricanteService fabricanteService;

    @Autowired
    public FabricanteController(FabricanteService fabricanteService,ModelMapper mapper) {
        this.fabricanteService = fabricanteService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<FabricanteDTO>> findAll() {
        List<FabricanteDTO> fabricanteDTOs = fabricanteService.findAll()
                .stream()
                .map(fabricante -> mapper.map(fabricante, FabricanteDTO.class))
                .toList();

        return ResponseEntity.ok().body(fabricanteDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(fabricanteService.findById(id), FabricanteDTO.class));
    }

    @PostMapping()
    public ResponseEntity<FabricanteDTO> insert(@RequestBody FabricanteDTO fabricanteDTO) {
        Long id = fabricanteService.insert(fabricanteDTO).getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(fabricanteService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> update(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        return ResponseEntity.ok(fabricanteService.update(id, fabricanteDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fabricanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
