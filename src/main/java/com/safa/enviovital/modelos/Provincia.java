package com.safa.enviovital.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "provincia",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"eventos"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"eventos"})

public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Evento> eventos;
}
