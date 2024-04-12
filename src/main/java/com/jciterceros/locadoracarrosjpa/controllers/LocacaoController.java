package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.LocacaoDTO;
import com.jciterceros.locadoracarrosjpa.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    @Autowired
    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @GetMapping()
    public ResponseEntity<List<LocacaoDTO>> findAll() {
        return ResponseEntity.ok().body(locacaoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocacaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(locacaoService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<LocacaoDTO> insert(@RequestBody LocacaoDTO locacaoDTO) {
        LocacaoDTO entityDTO = locacaoService.insert(locacaoDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

}
