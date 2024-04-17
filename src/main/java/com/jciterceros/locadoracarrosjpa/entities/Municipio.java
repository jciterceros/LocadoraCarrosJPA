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

    //    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Cliente> municipioClientes;

    // TODO: Estudar como utilizar a anotação @SQLJoinTableRestriction
    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Seguradora> municipioSeguradoras;

}
