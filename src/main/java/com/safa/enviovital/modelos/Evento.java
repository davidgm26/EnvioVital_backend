package com.safa.enviovital.modelos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "evento",schema = "enviovital", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"almacenes","provincia"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"almacenes","provincia"})
@NamedEntityGraph(
        name = "Evento.almacenes",
        attributeNodes =@NamedAttributeNode("almacenes")
)
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
    @JsonIgnore
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    @ManyToMany
    @JoinTable(
            name = "eventoalmacen",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_almacen")
    )
    private Set<Almacen> almacenes;

}
