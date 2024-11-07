package com.safa.enviovital.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "almacen",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @Column(name = "es_activo")
    private Boolean esActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    @JsonIgnore
    private Provincia provincia;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

}
