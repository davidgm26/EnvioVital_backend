package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Almacen;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenResponseDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String email;
    private Boolean esActivo;
    private Integer idProvincia;
    private Integer idUsuario;
    private String fotoUrl;


    public static AlmacenResponseDTO AlmacenResponseDtoFromAlmacen(Almacen almacen) {
        return AlmacenResponseDTO.builder()
                .id(almacen.getId())
                .nombre(almacen.getNombre())
                .direccion(almacen.getDireccion())
                .descripcion(almacen.getDescripcion())
                .email(almacen.getEmail())
                .idProvincia(almacen.getProvincia().getId())
                .esActivo(almacen.getEsActivo())
                .idUsuario(almacen.getUsuario().getId())
                .build();
    }
}
