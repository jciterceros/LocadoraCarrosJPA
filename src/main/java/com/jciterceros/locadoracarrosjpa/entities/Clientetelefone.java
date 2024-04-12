package com.jciterceros.locadoracarrosjpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cliente"})
@Table(name = "tb_clientetelefone")
public class Clientetelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}
