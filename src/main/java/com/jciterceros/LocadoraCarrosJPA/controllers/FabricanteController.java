package com.jciterceros.LocadoraCarrosJPA.controllers;

import com.jciterceros.LocadoraCarrosJPA.dto.FabricanteDTO;
import com.jciterceros.LocadoraCarrosJPA.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    // find all fabricantes
    @GetMapping()
    public List<FabricanteDTO> findAll() {
        return fabricanteService.findAll();
    }

    // find fabricante by id
    @GetMapping(value = "/{id}")
    public FabricanteDTO findById(@PathVariable Long id) {
        return fabricanteService.findById(id);
    }

    // save fabricante
    @PostMapping()
    public FabricanteDTO insert(@RequestBody FabricanteDTO fabricanteDTO) {
        System.out.println(fabricanteDTO.toString());
        return fabricanteService.insert(fabricanteDTO);
    }

    // update fabricante
    @PutMapping(value = "/{id}")
    public FabricanteDTO update(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        System.out.println(fabricanteDTO.toString());
        return fabricanteService.update(id, fabricanteDTO);
    }

    // delete fabricante
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        fabricanteService.delete(id);
    }
}
