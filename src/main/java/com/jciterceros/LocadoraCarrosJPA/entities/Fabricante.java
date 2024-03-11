package com.jciterceros.LocadoraCarrosJPA.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_fabricante")
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String nome;

    @OneToMany(mappedBy = "fabricante")
    private List<Modelo> modelos= new ArrayList<>();

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
    private Set<Carro> carro = new HashSet<>();

    public Fabricante() {
    }

    public Fabricante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<Modelo> getModelos() {
        return modelos;
    }

    public Set<Carro> getCarro() {
        return carro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fabricante that = (Fabricante) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Fabricante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
