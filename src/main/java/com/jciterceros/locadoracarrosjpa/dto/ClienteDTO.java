package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Clientetelefone;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.entities.Locacao;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDTO {
    private Long id;
    private String nome;
    private String rg;
    private String cpf;
    private String logradouro;
    private String cnh;
    private LocalDate datavencimentocnh;
    private String email;
    private Long id_estado;
    private Long id_municipio;

    @JsonIgnore
    private Estado estado;
    @JsonIgnore
    private Municipio municipio;
    @JsonIgnore
    private Set<Clientetelefone> clienteTelefones;
    @JsonIgnore
    private Set<Locacao> clienteLocacoes;
}
