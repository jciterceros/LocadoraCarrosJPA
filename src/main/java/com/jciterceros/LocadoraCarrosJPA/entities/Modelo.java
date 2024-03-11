package com.jciterceros.LocadoraCarrosJPA.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private Set<Carro> carro = new HashSet<>();

    public Modelo() {
    }

    public Modelo(String nome, Fabricante fabricante) {
        this.nome = nome;
        this.fabricante = fabricante;
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Set<Carro> getCarro() {
        return carro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modelo modelo = (Modelo) o;
        return Objects.equals(id, modelo.id) && Objects.equals(nome, modelo.nome) && Objects.equals(fabricante, modelo.fabricante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, fabricante);
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fabricante=" + fabricante +
                '}';
    }
}
