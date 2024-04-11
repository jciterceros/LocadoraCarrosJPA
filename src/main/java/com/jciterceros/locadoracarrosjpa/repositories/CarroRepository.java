package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

        Optional<Carro> findByPlaca(String placa);

        boolean existsByPlaca(String placa);

        boolean existsByPlacaAndIdNot(String placa, Long id);
}
