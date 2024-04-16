package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Carro;
import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

        Optional<Carro> findByPlaca(String placa);

        boolean existsByPlaca(String placa);

        boolean existsByPlacaAndIdNot(String placa, Long id);

        @Query(value = "SELECT obj FROM Carro obj JOIN FETCH obj.fabricante JOIN FETCH obj.modelo JOIN FETCH obj.locacoes")
        List<Carro> searchAll();
}
