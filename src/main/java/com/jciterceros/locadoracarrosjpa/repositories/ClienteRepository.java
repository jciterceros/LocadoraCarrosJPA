package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);

    List<Cliente> findByMunicipioId(Long id);

    boolean existsByMunicipioId(Long id);

    @Query(value = "SELECT obj FROM Cliente obj " +
            "JOIN FETCH obj.municipio JOIN FETCH obj.municipio.estado " +
            "LEFT JOIN FETCH obj.clienteLocacoes LEFT JOIN FETCH obj.clienteTelefones")
    List<Cliente> searchAll();

    @Query(value = "SELECT obj FROM Cliente obj " +
            "JOIN FETCH obj.municipio JOIN FETCH obj.municipio.estado " +
            "LEFT JOIN FETCH obj.clienteLocacoes LEFT JOIN FETCH obj.clienteTelefones " +
            "WHERE obj.id = :id")
    List<Cliente> searchById(Long id);
}
