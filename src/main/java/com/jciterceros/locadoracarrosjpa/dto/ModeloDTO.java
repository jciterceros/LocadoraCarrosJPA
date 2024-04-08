package com.jciterceros.locadoracarrosjpa.dto;

import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModeloDTO {
    private Long id;
    private String nome;
    private Long id_fabricante;
    private String nome_fabricante;

    public ModeloDTO(Long id, String nome, Fabricante fabricante) {
        this.id = id;
        this.nome = nome;
        this.id_fabricante = fabricante.getId();
        this.nome_fabricante = fabricante.getNome();
    }
}
