package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstadoDTO {
    private Long id;
    private String descricao;
    private String sigla;
    @JsonIgnore
    private Set<Municipio> estadoMunicipios;
    @JsonIgnore
    private Set<Cliente> estadoClientes;
    @JsonIgnore
    private Set<Seguradora> estadoSeguradoras;
}
