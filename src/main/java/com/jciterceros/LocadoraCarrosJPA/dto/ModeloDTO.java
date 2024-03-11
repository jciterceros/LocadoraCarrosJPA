package com.jciterceros.LocadoraCarrosJPA.dto;

import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;

public class ModeloDTO {
    private Long id;
    private String nome;
    //private FabricanteDTO fabricante;

    public ModeloDTO(Modelo modelo) {
    }

    public ModeloDTO(ModeloDTO modeloDTO) {
        this.id = modeloDTO.getId();
        this.nome = modeloDTO.getNome();
        //this.fabricante.setId(fabricante.getId());
        //this.fabricante.setNome(fabricante.getNome());
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

//    public FabricanteDTO getFabricante() {
//        return fabricante;
//    }
//
//    public void setFabricante(FabricanteDTO fabricante) {
//        this.fabricante = fabricante;
//    }
}
