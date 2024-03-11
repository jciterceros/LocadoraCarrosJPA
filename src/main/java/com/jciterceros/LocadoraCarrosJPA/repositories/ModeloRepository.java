package com.jciterceros.LocadoraCarrosJPA.repositories;

import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository  extends JpaRepository<Modelo, Long> {

}
