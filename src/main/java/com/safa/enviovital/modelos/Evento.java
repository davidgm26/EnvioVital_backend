package com.safa.enviovital.modelos;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evento",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "es_activo")
    private Boolean esActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

}
