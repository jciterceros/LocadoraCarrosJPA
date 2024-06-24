package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "tb_carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @Column(nullable = false, length = 10)
    private String cor;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disponivel;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal valorLocacao;

    @Column(nullable = false)
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Locacao> locacoes = new HashSet<>();
}
