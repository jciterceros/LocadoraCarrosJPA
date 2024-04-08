package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    Optional<Fabricante> findByNome(String nome);
}
