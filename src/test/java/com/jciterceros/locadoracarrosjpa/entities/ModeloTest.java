package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeloTest {

    @DisplayName("Testa construtor, instanciando um objeto Modelo com argumentos")
    @Test
    void testConstructorWithAllArgs() {
        // Given (dado que)
        Long id = 1L;
        String nome = "Fiesta";
        String nomeFabricante = "Ford";
        // When (quando)
        Fabricante fabricante = new Fabricante(1L, nomeFabricante, null, null);
        Modelo modelo = new Modelo(id, nome, fabricante, null);
        // Then (então)
        assertEquals(id, modelo.getId());
        assertEquals(nome, modelo.getNome());
        assertEquals(fabricante, modelo.getFabricante());
        assertNull(modelo.getCarro());
    }

    @DisplayName("Testa construtor, instanciando um objeto Modelo sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Modelo modelo = new Modelo();
        // When (quando)
        // Then (então)
        assertNotNull(modelo);
    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // Given (dado que)
        Modelo modelo = new Modelo();
        Long id = 1L;
        String nome = "Fiesta";
        String nomeFabricante = "Ford";
        Fabricante fabricante = new Fabricante(1L, nomeFabricante, null, null);
        // When (quando)
        modelo.setId(id);
        modelo.setNome(nome);
        modelo.setFabricante(fabricante);
        modelo.setCarro(null);
        // Then (então)
        assertEquals(id, modelo.getId());
        assertEquals(nome, modelo.getNome());
        assertEquals(fabricante, modelo.getFabricante());
        assertNull(modelo.getCarro());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo1 = new Modelo(1L, "Fiesta", fabricante, null);
        Modelo modelo2 = new Modelo(1L, "Fiesta", fabricante, null);
        // When (quando)
        // Then (então)
        assertEquals(modelo1, modelo2);
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo1 = new Modelo(1L, "Fiesta", fabricante, null);
        Modelo modelo2 = new Modelo(1L, "Fiesta", fabricante, null);
        // When (quando)
        // Then (então)
        assertEquals(modelo1.hashCode(), modelo2.hashCode());
    }

    @DisplayName("Testa o metodo toString, retornando uma string com os valores dos atributos")
    @Test
    void testToString() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);
        // When (quando)
        String result = modelo.toString();
        // Then (então)
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Fiesta"));
    }

    @DisplayName("Testa o metodo setCarro, adicionando um carro a lista de carros do modelo")
    @Test
    void testSetCarro() {
        // Given (dado que)
        Modelo modelo = new Modelo();
        Carro carro = new Carro();
        // When (quando)
        modelo.getCarro().add(carro);
        // Then (então)
        assertEquals(1, modelo.getCarro().size());
    }
}