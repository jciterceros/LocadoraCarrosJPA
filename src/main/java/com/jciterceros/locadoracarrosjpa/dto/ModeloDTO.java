package com.jciterceros.locadoracarrosjpa.dto;

import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModeloDTO {
    private Long id;
    private String nome;
    private Long id_fabricante;//id_fabricante
    private String nome_fabricante;

    public ModeloDTO(Long id, String nome, Fabricante fabricante) {
        this.id = id;
        this.nome = nome;
        this.id_fabricante = fabricante.getId();
        this.nome_fabricante = fabricante.getNome();
    }

    @Override
    public String toString() {
        return "ModeloDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", id_fabricante=" + id_fabricante +
                ", nome_fabricante='" + nome_fabricante + '\'' +
                '}';
    }
}
