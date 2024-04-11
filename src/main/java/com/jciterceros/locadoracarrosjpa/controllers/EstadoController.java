package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.EstadoDTO;
import com.jciterceros.locadoracarrosjpa.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping()
    public ResponseEntity<List<EstadoDTO>> findAll() {
        return ResponseEntity.ok().body(estadoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(estadoService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<EstadoDTO> insert(@RequestBody EstadoDTO estadoDTO) {
        EstadoDTO entityDTO = estadoService.insert(estadoDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok().body(estadoService.update(id, estadoDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
