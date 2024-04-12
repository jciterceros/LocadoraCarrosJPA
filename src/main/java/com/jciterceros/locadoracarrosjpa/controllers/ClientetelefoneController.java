package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.ClientetelefoneDTO;
import com.jciterceros.locadoracarrosjpa.services.ClientetelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientetelefones")
public class ClientetelefoneController {

    private final ClientetelefoneService clientetelefoneService;

    @Autowired
    public ClientetelefoneController(ClientetelefoneService clientetelefoneService) {
        this.clientetelefoneService = clientetelefoneService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientetelefoneDTO>> findAll() {
        return ResponseEntity.ok().body(clientetelefoneService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientetelefoneDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientetelefoneService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ClientetelefoneDTO> insert(@RequestBody ClientetelefoneDTO clientetelefoneDTO) {
        ClientetelefoneDTO entityDTO = clientetelefoneService.insert(clientetelefoneDTO);
        Long id = entityDTO.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(entityDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientetelefoneDTO> update(@PathVariable Long id, @RequestBody ClientetelefoneDTO clientetelefoneDTO) {
        return ResponseEntity.ok().body(clientetelefoneService.update(id, clientetelefoneDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientetelefoneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
