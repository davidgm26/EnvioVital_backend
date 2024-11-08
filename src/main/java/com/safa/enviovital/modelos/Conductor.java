package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "conductor",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"usuario","almacenes"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"usuario","almacenes"})

public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private String dni;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "conductoralmacen",
            joinColumns = @JoinColumn(name = "id_conductor"),
            inverseJoinColumns = @JoinColumn(name = "id_almacen")
    )
    private Set<Almacen> almacenes;

}
