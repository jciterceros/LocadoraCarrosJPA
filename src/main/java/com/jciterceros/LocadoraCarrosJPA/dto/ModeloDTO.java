package com.jciterceros.LocadoraCarrosJPA.dto;

import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;

import java.util.HashSet;
import java.util.Set;

public class ModeloDTO {
    private Long id;
    private String nome;
    private Fabricante fabricante;

    public ModeloDTO() {
    }

    public ModeloDTO(Modelo modelo) {
        this.id = modelo.getId();
        this.nome = modelo.getNome();
        this.fabricante = modelo.getFabricante();

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

    public String getFabricante() {
        return fabricante.getId() + " - " + fabricante.getNome();
    }


}
