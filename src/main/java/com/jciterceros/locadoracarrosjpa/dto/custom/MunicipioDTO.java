package com.jciterceros.locadoracarrosjpa.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MunicipioDTO {

    private Long id;
    private String descricao;
    private Long id_estado;
    @JsonIgnore
    private Estado estado;
    @JsonIgnore
    private Set<Cliente> municipioClientes;
    @JsonIgnore
    private Set<Seguradora> municipioSeguradoras;
}
