package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByDescricaoAndEstadoId(String descricao, Long idEstado);

    boolean existsByDescricaoAndEstadoId(String descricao, Long idEstado);

}
