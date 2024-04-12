package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.SeguradoraDTO;
import com.jciterceros.locadoracarrosjpa.services.SeguradoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/seguradoras")
public class SeguradoraController {
    private final SeguradoraService seguradoraService;
    @Autowired
    public SeguradoraController(SeguradoraService seguradoraService) {
        this.seguradoraService = seguradoraService;
    }

    @GetMapping()
    public ResponseEntity<List<SeguradoraDTO>> findAll() {
        return ResponseEntity.ok().body(seguradoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SeguradoraDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(seguradoraService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<SeguradoraDTO> insert(@RequestBody SeguradoraDTO seguradoraDTO) {
        SeguradoraDTO entityDTO = seguradoraService.insert(seguradoraDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SeguradoraDTO> update(@PathVariable Long id, @RequestBody SeguradoraDTO seguradoraDTO) {
        return ResponseEntity.ok().body(seguradoraService.update(id, seguradoraDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seguradoraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
