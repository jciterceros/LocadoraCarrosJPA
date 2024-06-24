package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    @DisplayName("Testa construtor, instanciando um objeto Carro com argumentos")
    @Test
    void testConstructorWithAllArgs() {
        // Given (dado que)
        Long id = 1L;
        String placa = "ABC1234";
        String cor = "Preto";
        Boolean disponivel = false;
        BigDecimal valorLocacao = new BigDecimal("100.00");
        int ano = 2020;

        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);

        // When (quando)
        Carro carro = new Carro(id, placa, cor, disponivel, valorLocacao, ano, fabricante, modelo, null);
        // Then (então)
        assertEquals(id, carro.getId());
        assertEquals(placa, carro.getPlaca());
        assertEquals(cor, carro.getCor());
        assertFalse(carro.getDisponivel());
        assertEquals(valorLocacao, carro.getValorLocacao());
        assertEquals(ano, carro.getAno());
        assertEquals(fabricante, carro.getFabricante());
        assertEquals(modelo, carro.getModelo());
        assertNull(carro.getLocacoes());
    }

    @DisplayName("Testa construtor, instanciando um objeto Carro sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Carro carro = new Carro();
        // When (quando)
        // Then (então)
        assertNotNull(carro);
    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // Given (dado que)
        Carro carro = new Carro();
        Long id = 1L;
        String placa = "ABC1234";
        String cor = "Preto";
        Boolean disponivel = false;
        BigDecimal valorLocacao = new BigDecimal("100.00");
        int ano = 2020;

        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);

        // When (quando)
        carro.setId(id);
        carro.setPlaca(placa);
        carro.setCor(cor);
        carro.setDisponivel(disponivel);
        carro.setValorLocacao(valorLocacao);
        carro.setAno(ano);
        carro.setFabricante(fabricante);
        carro.setModelo(modelo);
        carro.setLocacoes(null);

        // Then (então)
        assertEquals(id, carro.getId());
        assertEquals(placa, carro.getPlaca());
        assertEquals(cor, carro.getCor());
        assertFalse(carro.getDisponivel());
        assertEquals(valorLocacao, carro.getValorLocacao());
        assertEquals(ano, carro.getAno());
        assertEquals(fabricante, carro.getFabricante());
        assertEquals(modelo, carro.getModelo());
        assertNull(carro.getLocacoes());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);
        Carro carro1 = new Carro(1L, "ABC1234", "Preto", false, new BigDecimal("100.00"), 2020, fabricante, modelo, null);
        Carro carro2 = new Carro(1L, "ABC1234", "Preto", false, new BigDecimal("100.00"), 2020, fabricante, modelo, null);
        // When (quando)
        // Then (então)
        assertEquals(carro1, carro2);
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);
        Carro carro1 = new Carro(1L, "ABC1234", "Preto", false, new BigDecimal("100.00"), 2020, fabricante, modelo, null);
        Carro carro2 = new Carro(1L, "ABC1234", "Preto", false, new BigDecimal("100.00"), 2020, fabricante, modelo, null);
        // When (quando)
        // Then (então)
        assertEquals(carro1.hashCode(), carro2.hashCode());
    }

    @DisplayName("Testa o metodo toString, representando o objeto em String")
    @Test
    void testToString() {
        // Given (dado que)
        Fabricante fabricante = new Fabricante(1L, "Ford", null, null);
        Modelo modelo = new Modelo(1L, "Fiesta", fabricante, null);
        Carro carro = new Carro(1L, "ABC1234", "Preto", false, new BigDecimal("100.00"), 2020, fabricante, modelo, null);
        // When (quando)
        String result = carro.toString();
        // Then (então)
        assertTrue(result.contains("1"));
        assertTrue(result.contains("ABC1234"));
        assertTrue(result.contains("Preto"));
        assertTrue(result.contains("false"));
        assertTrue(result.contains("100.00"));
        assertTrue(result.contains("2020"));
        assertTrue(result.contains("Fabricante"));
        assertTrue(result.contains("Modelo"));
    }

    @DisplayName("Testa o metodo setLocacoes, adicionando uma locacao a lista de locacoes do carro")
    @Test
    void testSetLocacoes() {
        // Given (dado que)
        Carro carro = new Carro();
        Locacao locacao = new Locacao();
        // When (quando)
        carro.getLocacoes().add(locacao);
        // Then (então)
        assertEquals(1, carro.getLocacoes().size());
    }

}