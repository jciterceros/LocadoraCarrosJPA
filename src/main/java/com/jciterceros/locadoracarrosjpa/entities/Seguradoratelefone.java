package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString(exclude = {"seguradora"})
@Table(name = "tb_seguradoratelefone")
public class Seguradoratelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_seguradora", nullable = false)
    private Seguradora seguradora;

}
