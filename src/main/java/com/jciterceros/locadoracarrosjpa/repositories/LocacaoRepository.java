package com.jciterceros.locadoracarrosjpa.repositories;

import com.jciterceros.locadoracarrosjpa.entities.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    boolean existsBySeguradoraId(Long id);

    //    @Query("SELECT l FROM Locacao l " +
//            "WHERE l.carro.id = :idCarro AND " +
//            "(:datalocacao BETWEEN l.datalocacao AND l.datadevolucao) OR " +
//            "(:datadevolucao BETWEEN l.datalocacao AND l.datadevolucao)")
    @Query("SELECT l FROM Locacao l " +
            "WHERE l.carro.id = :idCarro AND " +
            "(:datalocacao BETWEEN l.datalocacao AND l.datadevolvida) OR " +
            "(:datadevolucao BETWEEN l.datalocacao AND l.datadevolvida)")
    List<Locacao> findByCarroIdBetweenDatas(Long idCarro, LocalDate datalocacao, LocalDate datadevolucao);
}
