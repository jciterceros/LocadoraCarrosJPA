package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"seguradoraSeguradoratelefones", "seguradoraLocacoes"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @OneToMany(mappedBy = "seguradora", cascade = CascadeType.ALL)
    private Set<Seguradoratelefone> seguradoraSeguradoratelefones;

    @OneToMany(mappedBy = "seguradora", cascade = CascadeType.ALL)
    private Set<Locacao> seguradoraLocacoes;

}
