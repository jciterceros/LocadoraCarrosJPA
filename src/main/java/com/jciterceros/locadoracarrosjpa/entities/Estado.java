package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_estado")
public class Estado {

    //@Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 2)
    private String sigla;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private Set<Municipio> estadoMunicipios;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private Set<Cliente> estadoClientes;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private Set<Seguradora> estadoSeguradoras;

}
