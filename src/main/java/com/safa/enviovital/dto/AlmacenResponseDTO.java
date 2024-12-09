package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Almacen;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta de un almacen")
public class AlmacenResponseDTO {

    @Schema(
            description = "Identificador único del almacén.",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer id;

    @Schema(
            description = "Nombre del almacén.",
            example = "Almacén Central Madrid",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombre;

    @Schema(
            description = "Descripción breve del almacén.",
            example = "Almacén principal de distribución en Madrid."
    )
    private String descripcion;

    @Schema(
            description = "Dirección completa del almacén.",
            example = "Calle Mayor, 10, 28013 Madrid"
    )
    private String direccion;

    @Schema(
            description = "Correo electrónico del almacén.",
            example = "central.madrid@example.es"
    )
    private String email;

    @Schema(
            description = "Indica si el almacén está activo o inactivo.",
            example = "true"
    )
    private Boolean esActivo;

    @Schema(
            description = "Provincia en la que se encuentra el almacén.",
            example = "Sevilla"
    )
    private String provincia;

    @Schema(
            description = "ID del usuario asociado al almacén.",
            example = "7"
    )
    private Integer idUsuario;

    @Schema(
            description = "URL de la foto del almacén.",
            example = "https://example.com/fotos/almacen.jpg",
            nullable = true
    )
    private String fotoUrl;



    public static AlmacenResponseDTO AlmacenResponseDtoFromAlmacen(Almacen almacen) {
        return AlmacenResponseDTO.builder()
                .id(almacen.getId())
                .nombre(almacen.getNombre())
                .direccion(almacen.getDireccion())
                .descripcion(almacen.getDescripcion()).email(almacen.getEmail())
                .provincia(almacen.getProvincia().getNombre())
                .esActivo(almacen.getEsActivo())
                .idUsuario(almacen.getUsuario().getId())
                .build();
    }
}
