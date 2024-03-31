package com.jciterceros.LocadoraCarrosJPA.dto;

import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModeloDTOTest {

    private ModeloDTO modeloDTO;
    private Modelo modelo;
    private Fabricante fabricante;

    @BeforeEach
    void setUp() {
        fabricante = new Fabricante();
        fabricante.setId(1L);
        fabricante.setNome("Fabricante Test");

        modelo = new Modelo();
        modelo.setId(1L);
        modelo.setNome("Modelo Test");
        modelo.setFabricante(fabricante);

        modeloDTO = new ModeloDTO(modelo);
    }

    @Test
    void testGetId() {
        assertEquals(modelo.getId(), modeloDTO.getId());
    }

    @Test
    void testGetNome() {
        assertEquals(modelo.getNome(), modeloDTO.getNome());
    }

    @Test
    void testGetFabricante() {
        String expectedFabricante = fabricante.getId() + " - " + fabricante.getNome();
        assertEquals(expectedFabricante, modeloDTO.getFabricante());
    }
}
