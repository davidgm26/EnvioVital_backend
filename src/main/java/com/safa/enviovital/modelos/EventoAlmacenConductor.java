package com.safa.enviovital.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eventoalmacenconductor", schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"eventoAlmacen", "conductor"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"eventoAlmacen", "conductor"})
public class EventoAlmacenConductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eventoalmacen", nullable = false)
    private EventoAlmacen eventoAlmacen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conductor", nullable = false)
    private Conductor conductor;
}
