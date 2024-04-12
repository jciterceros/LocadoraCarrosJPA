package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Carro;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocacaoDTO {
    private Long id;
    private LocalDate datalocacao;
    private LocalDate datadevolucao;
    private LocalDate datadevolvida;
    private BigDecimal valor;
    private BigDecimal valordesconto;
    private BigDecimal valortotal;
    private Long id_carro;
    private Long id_cliente;
    private Long id_seguradora;

    @JsonIgnore
    private Carro carro;
    @JsonIgnore
    private Cliente cliente;
    @JsonIgnore
    private Seguradora seguradora;

}
