package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.entities.Locacao;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import com.jciterceros.locadoracarrosjpa.entities.Seguradoratelefone;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeguradoraDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private BigDecimal valor;
    private Long id_estado;
    private Long id_municipio;

    @JsonIgnore
    private Estado estado;
    @JsonIgnore
    private Municipio municipio;
    @JsonIgnore
    private Set<Seguradoratelefone> seguradoraSeguradoratelefones;
    @JsonIgnore
    private Set<Locacao> seguradoraLocacoes;
}
