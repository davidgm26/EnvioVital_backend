package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Provincia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenRequestDTO {
    private String nombre;
    private String descripcion;
    private String direccion;
    private String email;
    private Integer idProvincia;
    private UsuarioRequestDTO usuario;


    public static Almacen AlmacenRequestDtoToAlmacen(AlmacenRequestDTO almacenRequestDTO, Provincia provincia) {
        return Almacen.builder()
                .nombre(almacenRequestDTO.getNombre())
                .descripcion(almacenRequestDTO.getDescripcion())
                .direccion(almacenRequestDTO.getDireccion())
                .email(almacenRequestDTO.getEmail())
                .provincia(provincia)
                .build();
    }
}
