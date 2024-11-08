package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipovehiculo",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"vehiculos"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"vehiculos"})

public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipoVehiculo", fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos = new ArrayList<>();
}
