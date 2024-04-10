package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModeloDTO {
    private Long id;
    private String nome;
    @JsonIgnore
    private Fabricante fabricante;
    private Long id_fabricante;
    private String nome_fabricante;

}
