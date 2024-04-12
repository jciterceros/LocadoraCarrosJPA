package com.jciterceros.locadoracarrosjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientetelefoneDTO {
    private Long id;
    private String telefone;
    private Long id_cliente;
    @JsonIgnore
    private Cliente cliente;
}
