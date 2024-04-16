package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository  extends JpaRepository<Modelo, Long> {

    Optional<Modelo> findByNome(String nome);

    boolean existsByNomeAndFabricanteId(String nome, Long idFabricante);

    @Query(value = "SELECT obj FROM Modelo obj JOIN FETCH obj.fabricante")
    List<Modelo> searchAll();
}
