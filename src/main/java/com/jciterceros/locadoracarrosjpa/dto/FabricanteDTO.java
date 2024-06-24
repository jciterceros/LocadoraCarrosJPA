package com.jciterceros.locadoracarrosjpa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FabricanteDTO {
    private Long id;

    //    @JsonIgnore
    private String nome;

}
