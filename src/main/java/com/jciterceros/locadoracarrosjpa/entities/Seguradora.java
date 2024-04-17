package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"seguradoraTelefones", "seguradoraLocacoes"})
@Table(name = "tb_seguradora")
public class Seguradora {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;
    /**
     * O Relacionamento da Seguradora com Estado
     *
     * FetchType.LAZY = carrega somente quando necessário (quando for chamado), por padrao @OneToMany é LAZY
     * FetchType.EAGER = carrega sempre quando a entidade Pai for carregada, por padrao @ManyToOne é EAGER
     * */
    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @OneToMany(mappedBy = "seguradora", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Seguradoratelefone> seguradoraTelefones;

    @OneToMany(mappedBy = "seguradora", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Locacao> seguradoraLocacoes;

}
