package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evento", schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"almacenes", "provincia"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"almacenes", "provincia"})
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

    @ManyToMany
    @JoinTable(
            name = "eventoalmacen",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_almacen")
    )
    private Set<Almacen> almacenes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "eventoalmacenconductor",
            joinColumns = @JoinColumn(name = "id_eventoalmacen"),
            inverseJoinColumns = @JoinColumn(name = "id_conductor")
    )
    private Set<Conductor> conductores = new HashSet<>();
}