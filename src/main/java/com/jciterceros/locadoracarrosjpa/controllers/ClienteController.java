package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.ClienteDTO;
import com.jciterceros.locadoracarrosjpa.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO entityDTO = clienteService.insert(clienteDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok().body(clienteService.update(id, clienteDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // TODO:    @GetMapping(value = "/{id}/locacoes")
}
