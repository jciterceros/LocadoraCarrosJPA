package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.SeguradoratelefoneDTO;
import com.jciterceros.locadoracarrosjpa.services.SeguradoratelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/seguradoratelefone")
public class SeguradoratelefoneController {

    private final SeguradoratelefoneService seguradoratelefoneService;

    @Autowired
    public SeguradoratelefoneController(SeguradoratelefoneService seguradoratelefoneService) {
        this.seguradoratelefoneService = seguradoratelefoneService;
    }

    @GetMapping()
    public ResponseEntity<List<SeguradoratelefoneDTO>> findAll() {
        return ResponseEntity.ok().body(seguradoratelefoneService.findAll());
    }

    // find by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<SeguradoratelefoneDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(seguradoratelefoneService.findById(id));
    }

    // Insert
    @PostMapping()
    public ResponseEntity<SeguradoratelefoneDTO> insert(@RequestBody SeguradoratelefoneDTO seguradoratelefoneDTO) {
        SeguradoratelefoneDTO entityDTO = seguradoratelefoneService.insert(seguradoratelefoneDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }
}
