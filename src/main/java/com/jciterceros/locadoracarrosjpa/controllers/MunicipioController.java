package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.custom.MunicipioDTO;
import com.jciterceros.locadoracarrosjpa.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/municipios")
public class MunicipioController {
    private final MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping()
    public ResponseEntity<List<MunicipioDTO>> findAll() {
        return ResponseEntity.ok().body(municipioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MunicipioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(municipioService.findById(id));
    }

    // Insert
    @PostMapping()
    public ResponseEntity<MunicipioDTO> insert(@RequestBody MunicipioDTO municipioDTO) {
        MunicipioDTO entityDTO = municipioService.insert(municipioDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

    // Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<MunicipioDTO> update(@PathVariable Long id, @RequestBody MunicipioDTO municipioDTO) {
        return ResponseEntity.ok().body(municipioService.update(id, municipioDTO));
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        municipioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
