package com.safa.enviovital.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eventoalmacen",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"evento", "almacen"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"evento", "almacen"})
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

}
