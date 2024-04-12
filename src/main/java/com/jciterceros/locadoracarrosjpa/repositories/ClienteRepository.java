package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);

    List<Cliente> findByMunicipioId(Long id);

    boolean existsByMunicipioId(Long id);
}
