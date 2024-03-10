package com.jciterceros.LocadoraCarrosJPA.dto;

import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;

public class FabricanteDTO {
    private Long id;
    private String nome;

    public FabricanteDTO() {
    }

    public FabricanteDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FabricanteDTO(Fabricante fabricante) {
        this.id = fabricante.getId();
        this.nome = fabricante.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
