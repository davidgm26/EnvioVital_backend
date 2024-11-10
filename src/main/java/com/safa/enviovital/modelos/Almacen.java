package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "almacen", schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"eventoAlmacenes", "usuario", "provincia"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"eventoAlmacenes", "usuario", "provincia"})
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "direccion")
    private String direccion;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "es_activo")
    private Boolean esActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Provincia provincia;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventoAlmacen> eventoAlmacenes = new HashSet<>();
}