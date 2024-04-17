package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SeguradoraRepository extends JpaRepository<Seguradora, Long> {
    boolean existsByCnpj(String cnpj);
    boolean existsByMunicipioId(Long id);

    @Query(value = "SELECT obj FROM Seguradora obj " +
            "JOIN FETCH obj.municipio JOIN FETCH obj.municipio.estado " +
            "LEFT JOIN FETCH obj.seguradoraLocacoes LEFT JOIN FETCH obj.seguradoraTelefones")
    List<Seguradora> searchAll();

    @Query(value = "SELECT obj FROM Seguradora obj " +
            "JOIN FETCH obj.municipio JOIN FETCH obj.municipio.estado " +
            "LEFT JOIN FETCH obj.seguradoraLocacoes LEFT JOIN FETCH obj.seguradoraTelefones " +
            "WHERE obj.id = :id")
    List<Seguradora> searchById(Long id);
}
