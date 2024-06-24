package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"carro", "cliente", "seguradora"})
@Table(name = "tb_locacao")
public class Locacao {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate datalocacao;

    @Column(nullable = false)
    private LocalDate datadevolucao;

    @Column(nullable = false)
    private LocalDate datadevolvida;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal valordesconto;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal valortotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carro", nullable = false)
    private Carro carro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seguradora", nullable = false)
    private Seguradora seguradora;

}
