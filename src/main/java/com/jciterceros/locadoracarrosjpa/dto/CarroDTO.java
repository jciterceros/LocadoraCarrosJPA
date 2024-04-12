package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Fabricante;
import com.jciterceros.locadoracarrosjpa.entities.Locacao;
import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarroDTO {
        private Long id;
        private String placa;
        private String cor;
        private Boolean disponivel;
        private BigDecimal valorLocacao;
        private Integer ano;
        private Long id_fabricante;
        private String nome_fabricante;
        private Long id_modelo;
        private String nome_modelo;

        @JsonIgnore
        private Fabricante fabricante;
        @JsonIgnore
        private Modelo modelo;
        @JsonIgnore
        private Set<Locacao> locacoes;

}
