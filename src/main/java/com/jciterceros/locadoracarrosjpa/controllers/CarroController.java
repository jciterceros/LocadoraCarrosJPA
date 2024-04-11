package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.CarroDTO;
import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {

    private final CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    // finAll
    @GetMapping()
    public ResponseEntity<List<CarroDTO>> findAll() {
        return ResponseEntity.ok().body(carroService.findAll());
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(carroService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<CarroDTO> insert(@RequestBody CarroDTO carroDTO) {
        CarroDTO entityDTO = carroService.insert(carroDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        return ResponseEntity.ok().body(carroService.update(id, carroDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
