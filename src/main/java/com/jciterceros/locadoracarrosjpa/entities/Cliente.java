package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"clienteTelefones", "clienteLocacoes"})
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 9)
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 11)
    private String cnh;

    @Column(nullable = false)
    private LocalDate datavencimentocnh;

    @Column(nullable = false, length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Clientetelefone> clienteTelefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Locacao> clienteLocacoes = new HashSet<>();

}
