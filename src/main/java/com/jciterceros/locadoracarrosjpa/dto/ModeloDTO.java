package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

//    public ModeloDTO(Long id, String nome, Fabricante fabricante) {
//        this.id = id;
//        this.nome = nome;
//        this.id_fabricante = fabricante.getId();
//        this.nome_fabricante = fabricante.getNome();
//    }
}
