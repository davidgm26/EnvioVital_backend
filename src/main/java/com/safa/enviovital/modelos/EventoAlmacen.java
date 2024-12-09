package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventoalmacen", schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"evento", "almacen", "eventoAlmacenConductores"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"evento", "almacen", "eventoAlmacenConductores"})
public class EventoAlmacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_almacen", nullable = false)
    private Almacen almacen;

    @OneToMany(mappedBy = "eventoAlmacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventoAlmacenConductor> eventoAlmacenConductores = new HashSet<>();
}