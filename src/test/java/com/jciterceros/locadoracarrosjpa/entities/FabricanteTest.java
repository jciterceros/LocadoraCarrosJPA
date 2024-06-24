package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricanteTest {

    @DisplayName("Testa construtor, instanciando um objeto Fabricante com argumentos")
    @Test
    void testConstructorWithAllArgs() {
        // Given (dado que)
        Long id = 1L;
        String nome = "Ford";
        // When (quando)
        Fabricante fabricante = new Fabricante(id, nome, null, null);
        // Then (então)
        assertEquals(id, fabricante.getId());
        assertEquals(nome, fabricante.getNome());
    }

    @DisplayName("Testa construtor, instanciando um objeto Fabricante sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante();
        // When (quando)
        // Then (então)
        assertNotNull(fabricante);
    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante();
        Long id = 1L;
        String nome = "Ford";
        // When (quando)
        fabricante.setId(id);
        fabricante.setNome(nome);
        // Then (então)
        assertEquals(id, fabricante.getId());
        assertEquals(nome, fabricante.getNome());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Fabricante fabricante1 = new Fabricante(1L, "Ford", null, null);
        Fabricante fabricante2 = new Fabricante(1L, "Ford", null, null);
        // When (quando)
        // Then (então)
        assertEquals(fabricante1, fabricante2);
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Fabricante fabricante1 = new Fabricante(1L, "Ford", null, null);
        Fabricante fabricante2 = new Fabricante(1L, "Ford", null, null);
        // When (quando)
        // Then (então)
        assertEquals(fabricante1.hashCode(), fabricante2.hashCode());
    }

    @DisplayName("Testa o metodo toString, representando o objeto em String")
    @Test
    void testToString() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        // When (quando)
        String result = fabricante.toString();
        // Then (então)
        assertTrue(result.contains("Fabricante"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("nome=Ford"));
    }

    @DisplayName("Testa o metodo setModelo, adicionando um modelo a lista de modelos do fabricante")
    @Test
    void testSetModelo() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante();
        Modelo modelo = new Modelo();
        // When (quando)
        fabricante.getModelos().add(modelo);
        // Then (então)
        assertEquals(1, fabricante.getModelos().size());
        assertTrue(fabricante.getModelos().contains(modelo));
    }

    @DisplayName("Testa o metodo setCarro, adicionando um carro a lista de carros do fabricante")
    @Test
    void testSetCarro() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante();
        Carro carro = new Carro();
        // When (quando)
        fabricante.getCarro().add(carro);
        // Then (então)
        assertEquals(1, fabricante.getCarro().size());
        assertTrue(fabricante.getCarro().contains(carro));
    }
}