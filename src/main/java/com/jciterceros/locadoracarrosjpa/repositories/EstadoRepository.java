package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByDescricao(String descricao);

    // TODO: Melhorar métodos de busca por id e estado
    @Query("SELECT DISTINCT e FROM Estado e " +
            "RIGHT JOIN FETCH e.estadoMunicipios " +
            "LEFT JOIN FETCH e.estadoClientes")
    List<Estado> searchAll();

    @Query("SELECT DISTINCT e FROM Estado e " +
            "RIGHT JOIN FETCH e.estadoMunicipios " +
            "LEFT JOIN FETCH e.estadoClientes " +
            "WHERE e.id = :id")
    List<Estado> searchById(Long id);
}
