package com.jciterceros.locadoracarrosjpa.controllers;

import com.jciterceros.locadoracarrosjpa.dto.FabricanteDTO;
import com.jciterceros.locadoracarrosjpa.services.FabricanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    @Autowired
    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    // TODO: Add Swagger annotations
    // Change fabricante-controller to Fabricante

    @Operation(

            summary = "Find all fabricantes",
            description = "Retorna todos os fabricantes",
            method = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all fabricantes"),
            @ApiResponse(responseCode = "404", description = "Not found fabricantes"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping()
    public ResponseEntity<List<FabricanteDTO>> findAll() {
        return ResponseEntity.ok().body(fabricanteService.findAll());
    }

    @Operation(
            summary = "Find fabricante by id",
            description = "Retorna um fabricante pelo id",
            method = "GET"
    )
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(
                    name = "id",
                    description = "Id do fabricante",
                    required = true,
                    example = "1",
                    schema = @Schema(type = "integer")
            )
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found fabricante by id"),
            @ApiResponse(responseCode = "404", description = "Not found fabricante by id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(fabricanteService.findById(id));
    }

    @Operation(
            summary = "Insert a new fabricante",
            description = "Insere um novo fabricante",
            method = "POST"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Fabricante a ser inserido",
            required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FabricanteDTO.class))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new fabricante"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping()
    public ResponseEntity<FabricanteDTO> insert(@RequestBody FabricanteDTO fabricanteDTO) {
        Long id = fabricanteService.insert(fabricanteDTO).getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(fabricanteService.findById(id));
    }

    @Operation(summary = "Update fabricante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated fabricante"),
            @ApiResponse(responseCode = "404", description = "Not found fabricante"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> update(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        return ResponseEntity.ok().body(fabricanteService.update(id, fabricanteDTO));
    }

    @Operation(summary = "Delete fabricante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted fabricante"),
            @ApiResponse(responseCode = "404", description = "Not found fabricante"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fabricanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
