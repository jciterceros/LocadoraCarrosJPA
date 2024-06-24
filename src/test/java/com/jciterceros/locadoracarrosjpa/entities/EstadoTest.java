package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTest {

    private Estado estado;
    private Long id;
    private String descricao;
    private String sigla;

    @BeforeEach
    void setUp() {
        // Given (dado que)
        id = 1L;
        descricao = "São Paulo";
        sigla = "SP";
        // When (quando)
        estado = new Estado(id, descricao, sigla, null, null, null);
    }

    @DisplayName("Testa construtor, instanciando um objeto Estado com argumentos")
    @Test
    void testConstructorWithAllArgs() {
        // Then (então)
        assertEquals(id, estado.getId());
        assertEquals(descricao, estado.getDescricao());
        assertEquals(sigla, estado.getSigla());
    }

    @DisplayName("Testa construtor, instanciando um objeto Estado sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Estado estado = new Estado();
        // When (quando)
        // Then (então)
        assertNotNull(estado);
    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // When (quando)
        estado.setId(id);
        estado.setDescricao(descricao);
        estado.setSigla(sigla);
        // Then (então)
        assertEquals(id, estado.getId());
        assertEquals(descricao, estado.getDescricao());
        assertEquals(sigla, estado.getSigla());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Estado estado1 = new Estado(1L, "São Paulo", "SP", null, null, null);
        Estado estado2 = new Estado(1L, "São Paulo", "SP", null, null, null);
        // When (quando)
        // Then (então)
        assertEquals(estado1, estado2);
    }

    @DisplayName("Testa o metodo equals, comparando todos os campos de dois objetos iguais")
    @Test
    void testEqualsAllFields() {
        // Given (dado que)
        Estado estado2 = new Estado(id, descricao, sigla, null, null, null);
        // Then (então)
        assertEquals(estado.getId(), estado2.getId());
        assertEquals(estado.getDescricao(), estado2.getDescricao());
        assertEquals(estado.getSigla(), estado2.getSigla());
        assertEquals(estado, estado2);
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Estado estado1 = new Estado(1L, "São Paulo", "SP", null, null, null);
        Estado estado2 = new Estado(1L, "São Paulo", "SP", null, null, null);
        // When (quando)
        // Then (então)
        assertEquals(estado1.hashCode(), estado2.hashCode());
    }

    @DisplayName("Testa o metodo toString, retornando a representação em String do objeto")
    @Test
    void testToString() {
        // Given (dado que)
        Estado estado = new Estado(1L, "São Paulo", "SP", null, null, null);
        // When (quando)
        String result = estado.toString();
        // Then (então)
        assertEquals("Estado(id=1, descricao=São Paulo, sigla=SP)", result);
    }

    // Testing Set<Municipio> getEstadoMunicipios()
    @DisplayName("Testa os metodos Add e Get do SET, Verifica sé os Municipios foram inseridos no Estado")
    @Test
    void testSettersAndGettersEstadoMunicipios() {
        // Given (dado que)
        Estado estado = new Estado();
        // When (quando)
        // Then (então)
        assertNotNull(estado.getEstadoMunicipios());

        // Given (dado que)
        Municipio municipio = new Municipio();
        // When (quando)
        estado.getEstadoMunicipios().add(municipio);
        // Then (então)
        assertEquals(1, estado.getEstadoMunicipios().size());
    }

    // Testing Set<Cliente> getEstadoClientes()
    @DisplayName("Testa os metodos Add e Get do SET, Verifica sé os Clientes foram  inseridos no Estado")
    @Test
    void testSettersAndGettersEstadoClientes() {
        // Given (dado que)
        Estado estado = new Estado();
        // When (quando)
        // Then (então)
        assertNotNull(estado.getEstadoClientes());

        // Given (dado que)
        Cliente cliente = new Cliente();
        // When (quando)
        estado.getEstadoClientes().add(cliente);
        // Then (então)
        assertEquals(1, estado.getEstadoClientes().size());
    }

    // Testing Set<Seguradora> getEstadoSeguradoras()
    @DisplayName("Testa os metodos Add e Get do SET, Verifica sé as Seguradoras foram inseridas no Estado")
    @Test
    void testSettersAndGettersEstadoSeguradoras() {
        // Given (dado que)
        Estado estado = new Estado();
        // When (quando)
        // Then (então)
        assertNotNull(estado.getEstadoSeguradoras());

        // Given (dado que)
        Seguradora seguradora = new Seguradora();
        // When (quando)
        estado.getEstadoSeguradoras().add(seguradora);
        // Then (então)
        assertEquals(1, estado.getEstadoSeguradoras().size());
    }
}