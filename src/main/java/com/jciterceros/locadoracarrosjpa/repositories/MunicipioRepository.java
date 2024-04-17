package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByDescricaoAndEstadoId(String descricao, Long idEstado);

    boolean existsByDescricaoAndEstadoId(String descricao, Long idEstado);

    //    @Query("SELECT DISTINCT m FROM Municipio m JOIN FETCH m.estado.estadoMunicipios " +
//            "LEFT JOIN FETCH m.municipioClientes LEFT JOIN FETCH m.municipioSeguradoras")
//    @Query("SELECT m FROM Municipio m " +
//            "JOIN FETCH m.estado e " +
//            "JOIN FETCH e.estadoMunicipios " +
//            "LEFT JOIN FETCH m.municipioClientes " +
//            "LEFT JOIN FETCH m.municipioSeguradoras")
//    @Query("SELECT DISTINCT m FROM Municipio m")
//    @Query(value = "SELECT id, descricao, id_estado FROM tb_municipio", nativeQuery = true)
    @Query("SELECT DISTINCT m FROM Municipio m " +
            "JOIN FETCH m.estado e " +
            "JOIN FETCH e.estadoMunicipios em " +
            "LEFT JOIN FETCH m.municipioClientes mc " +
            "LEFT JOIN FETCH m.municipioSeguradoras ms")
    List<Municipio> searchAll();

    @Query("SELECT m FROM Municipio m " +
            "JOIN FETCH m.estado e " +
            "JOIN FETCH e.estadoMunicipios " +
            "LEFT JOIN FETCH m.municipioClientes " +
            "LEFT JOIN FETCH m.municipioSeguradoras " +
            "WHERE m.id = :id")
    List<Municipio> searchById(Long id);
}
