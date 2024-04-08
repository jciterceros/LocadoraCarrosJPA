package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
class ModeloTest {
//
//    private Long id;
//    private String nome;
//    private Fabricante fabricante;
//    private Set<Carro> carros = new HashSet<>();
//
//    private Modelo modelo;
//
//    @BeforeEach
//    void setUp() {
//        startModelo();
//    }
//
//    private void startModelo() {
//        // create a new Fabricante with id 1L and name "Ford" without any Modelos or Carros
//        fabricante = new Fabricante(1L, "Ford", null, null);
//
//        // create a new Modelo with id 1L and name "Corsa" with a Fabricante and a set of Carros
//        List<Modelo> modelos = new ArrayList<>();
//        modelo = new Modelo(1L, "Corsa", fabricante, null);
//        modelos.add(modelo);
//        fabricante.setModelos(modelos);
//
//        // create a new Carro with id 1L, name "Corsa", color "Preto", IsAvailable true, year 2000, rentalValue 20000.00, a Fabricante and a Modelo
//        carros.add(new Carro(1L, "Corsa", "Preto", true, 2000, 20000.00, fabricante, modelo));
//        fabricante.setCarro(carros);
//
//    }
//
//    @Test
//    void getId() {
//        // Given (dado que)
//        Long expected = 1L;
//        // When (quando)
//        Long result = modelo.getId();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getNome() {
//        // Given (dado que)
//        String expected = "Corsa";
//        // When (quando)
//        String result = modelo.getNome();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getFabricante() {
//        // Given (dado que)
//        Modelo modelo = new Modelo(1L, "Corsa", fabricante, null);
//        // When (quando)
//        Fabricante result = modelo.getFabricante();
//        // Then (então)
//        assertEquals(fabricante, result, "O Fabricante retornado deve ser o mesmo que foi associado ao Modelo");
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
//    void setFabricante() {
//        // Given (dado que)
//        Modelo modelo = new Modelo(1L, "Corsa", fabricante, null);
//        Fabricante expected = fabricante;
//        // When (quando)
//        modelo.setFabricante(fabricante);
//        Fabricante result = modelo.getFabricante();
//        // Then (então)
//        assertEquals(expected, result, "O Fabricante retornado deve ser o mesmo que foi associado ao Modelo");
//    }
//
//    @Test
//    void setCarro() {
//        // Given
//        Set<Carro> carros = new HashSet<>(); // Inicializa a lista de carros
//        int expected = 1; // Espera-se que haja 1 carro após a adição
//
//        // When
//        Carro novoCarro = new Carro(2L, "Celta", "Branco", true, 2000, 20000.00, null, null);
//        carros.add(novoCarro); // Adiciona o carro à lista de carros
//        int result = carros.size(); // Obtém o tamanho da lista de carros
//
//        // Then
//        assertEquals(expected, result); // Verifica se o tamanho da lista de carros é o esperado
//        assertEquals(novoCarro, carros.iterator().next()); // Verifica se o carro adicionado é o esperado
//
//    }
//
//    @Test
//    void testToString() {
//        // Given (dado que)
//        String expected = "Modelo(id=1, nome=Corsa)";
//        // When (quando)
//        String result = modelo.toString();
//        // Then (então)
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void testEquals() {
//        // Given
//        Modelo expected = new Modelo(1L, "Corsa", fabricante, carros);
//        // When
//        Modelo result = new Modelo(1L, "Corsa", fabricante, carros);
//        // Then
//        assertEquals(expected, result);
//    }
}