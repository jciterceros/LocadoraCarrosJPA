package com.jciterceros.locadoracarrosjpa.dto;

import lombok.*;

import java.math.BigDecimal;

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

}
