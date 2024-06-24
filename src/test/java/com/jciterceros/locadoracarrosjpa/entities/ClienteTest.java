package com.jciterceros.locadoracarrosjpa.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteTest {
    private Long id;
    private String nome;
    private String rg;
    private String cpf;
    private String logradouro;
    private String cnh;
    private LocalDate datavencimentocnh;
    private String email;
    private Estado estado;
    private Municipio municipio;
    private Set<Clientetelefone> clienteTelefones;
    private Set<Locacao> clienteLocacoes;

    @BeforeEach()
    void setUp() {
        // Given (dado que)
        id = 1L;
        nome = "João";
        rg = "123456789";
        cpf = "12345678900";
        logradouro = "Rua 1";
        cnh = "12345678900";
        datavencimentocnh = LocalDate.now();
        email = "Joao@email.com.br";
        clienteTelefones = new HashSet<>();
        clienteLocacoes = new HashSet<>();
        estado = new Estado();
        municipio = new Municipio();

    }

    @DisplayName("Testa construtor, instanciando um objeto Cliente com argumentos")
    @Test
    void testConstructorWithAllArgs() {

        // When (quando)
        Cliente cliente = new Cliente(id, nome, rg, cpf, logradouro, cnh, datavencimentocnh, email, estado, municipio, clienteTelefones, clienteLocacoes);

        // Then (então)
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(rg, cliente.getRg());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(logradouro, cliente.getLogradouro());
        assertEquals(cnh, cliente.getCnh());
        assertEquals(datavencimentocnh, cliente.getDatavencimentocnh());
        assertEquals(email, cliente.getEmail());
        assertEquals(estado, cliente.getEstado());
        assertEquals(municipio, cliente.getMunicipio());
        assertEquals(clienteTelefones, cliente.getClienteTelefones());
        assertEquals(clienteLocacoes, cliente.getClienteLocacoes());

    }

    @DisplayName("Testa construtor, instanciando um objeto Cliente sem argumentos")
    @Test
    void testConstructorWithoutArgs() {
        // Given (dado que)
        Cliente cliente = new Cliente();
        // When (quando)
        // Then (então)
        assertNull(cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getRg());
        assertNull(cliente.getCpf());
        assertNull(cliente.getLogradouro());
        assertNull(cliente.getCnh());
        assertNull(cliente.getDatavencimentocnh());
        assertNull(cliente.getEmail());
        assertNull(cliente.getEstado());
        assertNull(cliente.getMunicipio());
        assertNotNull(cliente.getClienteTelefones());
        assertNotNull(cliente.getClienteLocacoes());

    }

    @DisplayName("Testa os metodos setters e getters, setando valores e pegando valores")
    @Test
    void testSettersAndGetters() {
        // When (quando)
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setRg(rg);
        cliente.setCpf(cpf);
        cliente.setLogradouro(logradouro);
        cliente.setCnh(cnh);
        cliente.setDatavencimentocnh(datavencimentocnh);
        cliente.setEmail(email);
        cliente.setEstado(estado);
        cliente.setMunicipio(municipio);
        cliente.setClienteTelefones(clienteTelefones);
        cliente.setClienteLocacoes(clienteLocacoes);
        // Then (então)
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(rg, cliente.getRg());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(logradouro, cliente.getLogradouro());
        assertEquals(cnh, cliente.getCnh());
        assertEquals(datavencimentocnh, cliente.getDatavencimentocnh());
        assertEquals(email, cliente.getEmail());
        assertEquals(estado, cliente.getEstado());
        assertEquals(municipio, cliente.getMunicipio());
        assertEquals(clienteTelefones, cliente.getClienteTelefones());
        assertEquals(clienteLocacoes, cliente.getClienteLocacoes());
    }

    @DisplayName("Testa o metodo equals, comparando dois objetos iguais")
    @Test
    void testEquals() {
        // Given (dado que)
        Cliente cliente1 = new Cliente(1L, "João", "123456789", "12345678900", "Rua 1", "12345678900", LocalDate.now(), "Joao@email.com",
                new Estado(), new Municipio(), new HashSet<>(), new HashSet<>());
        Cliente cliente2 = new Cliente(1L, "João", "123456789", "12345678900", "Rua 1", "12345678900", LocalDate.now(), "Joao@email.com",
                new Estado(), new Municipio(), new HashSet<>(), new HashSet<>());
        // When (quando)
        // Then (então)
        assertEquals(cliente1, cliente2);
    }

    @DisplayName("Testa o metodo equals, comparando todos os campos de dois objetos iguais")
    @Test
    void testEqualsAllFields() {
        // Given (dado que)
        Cliente cliente = new Cliente(id, nome, rg, cpf, logradouro, cnh, datavencimentocnh, email, estado, municipio, clienteTelefones, clienteLocacoes);
        // Then (então)
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(rg, cliente.getRg());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(logradouro, cliente.getLogradouro());
        assertEquals(cnh, cliente.getCnh());
        assertEquals(datavencimentocnh, cliente.getDatavencimentocnh());
        assertEquals(email, cliente.getEmail());
        assertEquals(estado, cliente.getEstado());
        assertEquals(municipio, cliente.getMunicipio());
        assertEquals(clienteTelefones, cliente.getClienteTelefones());
        assertEquals(clienteLocacoes, cliente.getClienteLocacoes());
    }

    @DisplayName("Testa o metodo hashCode, retornando o mesmo valor para dois objetos iguais")
    @Test
    void testHashCode() {
        // Given (dado que)
        Cliente cliente1 = new Cliente(1L, "João", "123456789", "12345678900", "Rua 1", "12345678900", LocalDate.now(), "Joao@email.com.br",
                new Estado(), new Municipio(), new HashSet<>(), new HashSet<>());
        Cliente cliente2 = new Cliente(1L, "João", "123456789", "12345678900", "Rua 1", "12345678900", LocalDate.now(), "Joao@email.com.br",
                new Estado(), new Municipio(), new HashSet<>(), new HashSet<>());
        // When (quando)
        // Then (então)
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @DisplayName("Testa o metodo toString, retornando uma string com os valores dos atributos")
    @Test
    void testToString() {
        // Given (dado que)
        Cliente cliente = new Cliente(id, nome, rg, cpf, logradouro, cnh, datavencimentocnh, email, estado, municipio, clienteTelefones, clienteLocacoes);
        // When (quando)
        String result = cliente.toString();
        // Then (então)
        assertEquals(
                "Cliente(id=1, nome=João, rg=123456789, cpf=12345678900, logradouro=Rua 1, cnh=12345678900, datavencimentocnh="
                        + datavencimentocnh
                        + ", email=Joao@email.com.br"
                        + ", estado=Estado(id=null, descricao=null, sigla=null)"
                        + ", municipio=Municipio(id=null, descricao=null, estado=null))"
                , result);
    }

    @DisplayName("Testa o metodo setClienteTelefone, adicionando um telefone a lista de telefones do cliente")
    @Test
    void testSetClienteTelefone() {
        // Given (dado que)
        Cliente cliente = new Cliente();
        Clientetelefone clientetelefone = new Clientetelefone();
        // When (quando)
        cliente.getClienteTelefones().add(clientetelefone);
        // Then (então)
        assertEquals(1, cliente.getClienteTelefones().size());
        assertEquals(clientetelefone, cliente.getClienteTelefones().iterator().next());
    }

    @DisplayName("Testa o metodo setClienteLocacao, adicionando uma locacao a lista de locacoes do cliente")
    @Test
    void testSetClienteLocacao() {
        // Given (dado que)
        Cliente cliente = new Cliente();
        Locacao locacao = new Locacao();
        // When (quando)
        cliente.getClienteLocacoes().add(locacao);
        // Then (então)
        assertEquals(1, cliente.getClienteLocacoes().size());
        assertEquals(locacao, cliente.getClienteLocacoes().iterator().next());
    }
}