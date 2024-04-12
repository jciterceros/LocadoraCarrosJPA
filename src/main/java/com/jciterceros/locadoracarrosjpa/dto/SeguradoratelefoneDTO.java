package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeguradoratelefoneDTO {
    private Long id;
    private String telefone;
    private Long id_seguradora;

    @JsonIgnore
    private Seguradora seguradora;
}
