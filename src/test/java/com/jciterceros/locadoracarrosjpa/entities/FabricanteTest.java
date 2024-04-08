package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class FabricanteTest {
//
//    private Fabricante fabricante;
//    private List<Modelo> modelos;
//    private Set<Carro> carros;
//
//    @BeforeEach
//    void setUp() {
//        startFabricante();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    private void startFabricante() {
//        // create a new Fabricante with id 1L and name "Ford" without any Modelos or Carros
//        fabricante = new Fabricante(1L, "Ford", null, null);
//
//        // create a new Modelo with id 1L and name "Corsa" with a Fabricante and a set of Carros
//        modelos = new ArrayList<>();
//        Modelo modelo = new Modelo(1L, "Corsa", fabricante, null);
//        modelos.add(modelo);
//        fabricante.setModelos(modelos);
//
//        // create a new Carro with id 1L, name "Corsa", color "Preto", IsAvailable true, year 2000, rentalValue 20000.00, a Fabricante and a Modelo
//        carros = new HashSet<>();
//        carros.add(new Carro(1L, "Corsa", "Preto", true, 2000, 20000.00, fabricante, modelo));
//        fabricante.setCarro(carros);
//    }
//
//    @Test
//    void getId() {
//        // Given (dado que)
//        Long expected = 1L;
//        // When (quando)
//        Long result = fabricante.getId();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getNome() {
//        // Given (dado que)
//        String expected = "Ford";
//        // When (quando)
//        String result = fabricante.getNome();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getModelos() {
//        // Given (dado que)
//        int expected = 1;
//        // When (quando)
//        int result = fabricante.getModelos().size();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getCarro() {
//        // Given (dado que)
//        int expected = 1;
//        // When (quando)
//        int result = fabricante.getCarro().size();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void setId() {
//        // Given (dado que)
//        Long expected = 2L;
//        // When (quando)
//        fabricante.setId(2L);
//        Long result = fabricante.getId();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void setNome() {
//        // Given (dado que)
//        String expected = "Chevrolet";
//        // When (quando)
//        fabricante.setNome("Chevrolet");
//        String result = fabricante.getNome();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void setModelos() {
//        // Given (dado que)
//        int expected = 2;
//        // When (quando)
//        modelos.add(new Modelo(2L, "Celta", fabricante, null));
//        fabricante.setModelos(modelos);
//        int result = fabricante.getModelos().size();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void setCarro() {
//        // Given (dado que)
//        int expected = 2;
//        // When (quando)
//        Carro novoCarro = new Carro(2L, "Celta", "Branco", true, 2000, 20000.00, fabricante, modelos.get(0));
//        carros.add(novoCarro);
//        fabricante.setCarro(carros);
//        int result = fabricante.getCarro().size();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void testToString() {
//        // Given (dado que)
//        String expected = "Fabricante(id=1, nome=Ford)";
//
//        // When (quando)
//        String result = fabricante.toString();
//
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void testEquals() {
//        // Given
//        Fabricante expected = new Fabricante(1L, "Ford", null, null);
//
//        // When
//        Fabricante result = new Fabricante(1L, "Ford", null, null);
//
//        // Then
//        assertEquals(expected, result);
//    }
}