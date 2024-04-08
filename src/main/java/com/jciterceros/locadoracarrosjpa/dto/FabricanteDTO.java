package com.jciterceros.locadoracarrosjpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FabricanteDTO {
    private Long id;
    private String nome;

    public FabricanteDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "FabricanteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
