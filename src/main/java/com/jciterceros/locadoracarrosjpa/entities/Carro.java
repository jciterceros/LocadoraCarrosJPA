package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    public Fabricante getFabricante() {
        return fabricante;
    }

    public Modelo getModelo() {
        return modelo;
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
                '}';
    }
}
