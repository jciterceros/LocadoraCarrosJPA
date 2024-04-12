package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SeguradoraRepository extends JpaRepository<Seguradora, Long> {
    boolean existsByCnpj(String cnpj);
    boolean existsByMunicipioId(Long id);

}
