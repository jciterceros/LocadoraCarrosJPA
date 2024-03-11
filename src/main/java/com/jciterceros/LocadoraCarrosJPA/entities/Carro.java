package com.jciterceros.LocadoraCarrosJPA.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, columnDefinition = "VARCHAR(7) NOT NULL")
    private String placa;
    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private String cor;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
    private Boolean disponivel;
    @Column(columnDefinition = "INTEGER NOT NULL")
    private Integer ano;
    @Column(columnDefinition = "DOUBLE PRECISION DEFAULT 12.2 NOT NULL")
    private Double valorLocacao;

//    private Long idFabricante;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    public Carro() {
    }

    public Carro(Long idFabricante, Long idModelo, String placa, String cor, Boolean disponivel, Integer ano, Double valorlocacao) {
        this.placa = placa;
        this.cor = cor;
        this.disponivel = disponivel;
        this.ano = ano;
        this.valorLocacao = valorlocacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(Double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public Modelo getModelo() {
        return modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id) && Objects.equals(placa, carro.placa) && Objects.equals(cor, carro.cor) && Objects.equals(disponivel, carro.disponivel) && Objects.equals(ano, carro.ano) && Objects.equals(valorLocacao, carro.valorLocacao) && Objects.equals(fabricante, carro.fabricante) && Objects.equals(modelo, carro.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa, cor, disponivel, ano, valorLocacao, fabricante, modelo);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", cor='" + cor + '\'' +
                ", disponivel=" + disponivel +
                ", ano=" + ano +
                ", valorLocacao=" + valorLocacao +
                ", fabricante=" + fabricante +
                ", modelo=" + modelo +
                '}';
    }
}
