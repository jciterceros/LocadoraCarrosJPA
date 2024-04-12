package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"municipioClientes", "municipioSeguradoras"})
@Table(name = "tb_municipio")
public class Municipio {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private Set<Cliente> municipioClientes;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private Set<Seguradora> municipioSeguradoras;

}
