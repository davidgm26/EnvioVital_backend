package com.safa.enviovital.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "almacen",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"conductores", "eventos", "usuario", "provincia"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"conductores", "eventos", "usuario", "provincia"})
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name ="direccion")
    private String direccion;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "es_activo")
    private Boolean esActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;


    @ManyToMany(mappedBy = "almacenes")
    @JsonIgnore
    private Set<Conductor> conductores;

    @ManyToMany(mappedBy = "almacenes")
    @JsonIgnore
    private Set<Evento> eventos;



}
