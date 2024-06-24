package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"estadoMunicipios", "estadoClientes", "estadoSeguradoras"})
@Table(name = "tb_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 2)
    private String sigla;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Municipio> estadoMunicipios = new HashSet<>();

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cliente> estadoClientes = new HashSet<>();

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Seguradora> estadoSeguradoras = new HashSet<>();

}
