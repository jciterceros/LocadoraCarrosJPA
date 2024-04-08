package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.FabricanteDTO;
import com.jciterceros.locadoracarrosjpa.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteController {

    private FabricanteService fabricanteService;

    @Autowired
    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @GetMapping()
    public List<FabricanteDTO> findAll() {
        return fabricanteService.findAll();
    }

    @GetMapping(value = "/{id}")
    public FabricanteDTO findById(@PathVariable Long id) {
        return fabricanteService.findById(id);
    }

    @PostMapping()
    public FabricanteDTO insert(@RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.insert(fabricanteDTO);
    }

    @PutMapping(value = "/{id}")
    public FabricanteDTO update(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.update(id, fabricanteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        fabricanteService.delete(id);
    }
}
