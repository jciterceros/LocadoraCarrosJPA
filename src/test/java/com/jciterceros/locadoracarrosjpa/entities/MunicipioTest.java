package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioTest {

    @DisplayName("Testa construtor, instanciando um objeto Municipio com argumentos")
    @Test
    void testConstructorWithAllArgs() {
        // Given (dado que)
        Long id = 1L;
        String descricao = "São Paulo";
        Estado estado = new Estado();
        Set<Cliente> municipioClientes = new HashSet<>();
        Set<Seguradora> municipioSeguradoras = new HashSet<>();
        // When (quando)
        Municipio municipio = new Municipio(id, descricao, estado, municipioClientes, municipioSeguradoras);
        // Then (então)
        assertEquals(id, municipio.getId());
        assertEquals(descricao, municipio.getDescricao());
        assertEquals(estado, municipio.getEstado());
        assertEquals(municipioClientes, municipio.getMunicipioClientes());
        assertEquals(municipioSeguradoras, municipio.getMunicipioSeguradoras());
    }

    @DisplayName("Testa construtor, instanciando um objeto Municipio sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Municipio municipio = new Municipio();
        // When (quando)
        // Then (então)
        assertNotNull(municipio);
    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // Given (dado que)
        Municipio municipio = new Municipio();
        Long id = 1L;
        String descricao = "São Paulo";
        Estado estado = new Estado();
        Set<Cliente> municipioClientes = new HashSet<>();
        Set<Seguradora> municipioSeguradoras = new HashSet<>();
        // When (quando)
        municipio.setId(id);
        municipio.setDescricao(descricao);
        municipio.setEstado(estado);
        municipio.setMunicipioClientes(municipioClientes);
        municipio.setMunicipioSeguradoras(municipioSeguradoras);
        // Then (então)
        assertEquals(id, municipio.getId());
        assertEquals(descricao, municipio.getDescricao());
        assertEquals(estado, municipio.getEstado());
        assertEquals(municipioClientes, municipio.getMunicipioClientes());
        assertEquals(municipioSeguradoras, municipio.getMunicipioSeguradoras());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Municipio municipio1 = new Municipio(1L, "São Paulo", new Estado(), new HashSet<>(), new HashSet<>());
        Municipio municipio2 = new Municipio(1L, "São Paulo", new Estado(), new HashSet<>(), new HashSet<>());
        // When (quando)
        // Then (então)
        assertEquals(municipio1, municipio2);
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Municipio municipio1 = new Municipio(1L, "São Paulo", new Estado(), new HashSet<>(), new HashSet<>());
        Municipio municipio2 = new Municipio(1L, "São Paulo", new Estado(), new HashSet<>(), new HashSet<>());
        // When (quando)
        // Then (então)
        assertEquals(municipio1.hashCode(), municipio2.hashCode());
    }

    @DisplayName("Testa o metodo toString, retornando uma string com os valores dos atributos")
    @Test
    void testToString() {
        // Given (dado que)
        Long id = 1L;
        String descricao = "São Paulo";
        Estado estado = new Estado();
        Set<Cliente> municipioClientes = new HashSet<>();
        Set<Seguradora> municipioSeguradoras = new HashSet<>();
        Municipio municipio = new Municipio(id, descricao, estado, municipioClientes, municipioSeguradoras);
        // When (quando)
        String expected = "Municipio(id=1, descricao=São Paulo, estado=Estado(id=null, descricao=null, sigla=null))";
        // Then (então)
        assertEquals(expected, municipio.toString());
    }

    @DisplayName("Testa o metodos Add e Get do SET<Cliente> municipioClientes, retornando um Set de Cliente")
    @Test
    void testAddAndGetMunicipioClientes() {
        // Given (dado que)
        Long id = 1L;
        String descricao = "São Paulo";
        Set<Cliente> municipioClientes = new HashSet<>();
        Estado estado = new Estado();
        Set<Seguradora> municipioSeguradoras = new HashSet<>();
        Municipio municipio = new Municipio(id, descricao, estado, municipioClientes, municipioSeguradoras);
        // When (quando)
        Set<Cliente> result = municipio.getMunicipioClientes();
        // Then (então)
        assertEquals(municipioClientes, result);

        // Given (dado que)
        Cliente cliente = new Cliente();
        // When (quando)
        municipio.getMunicipioClientes().add(cliente);
        // Then (então)
        assertEquals(1, municipio.getMunicipioClientes().size());
    }

    @DisplayName("Testa o metodos Add e Get do SET<Seguradora> municipioSeguradoras, retornando um Set de Seguradora")
    @Test
    void testAddAndGetMunicipioSeguradoras() {
        // Given (dado que)
        Long id = 1L;
        String descricao = "São Paulo";
        Set<Cliente> municipioClientes = new HashSet<>();
        Estado estado = new Estado();
        Set<Seguradora> municipioSeguradoras = new HashSet<>();
        Municipio municipio = new Municipio(id, descricao, estado, municipioClientes, municipioSeguradoras);
        // When (quando)
        Set<Seguradora> result = municipio.getMunicipioSeguradoras();
        // Then (então)
        assertEquals(municipioSeguradoras, result);

        // Given (dado que)
        Seguradora seguradora = new Seguradora();
        // When (quando)
        municipio.getMunicipioSeguradoras().add(seguradora);
        // Then (então)
        assertEquals(1, municipio.getMunicipioSeguradoras().size());
    }

}