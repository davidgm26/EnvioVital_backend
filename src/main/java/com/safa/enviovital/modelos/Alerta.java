package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Boolean vista = false;
}