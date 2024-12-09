package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.servicios.TipoVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tiposVehiculo")
@AllArgsConstructor
public class TipoVehiculoControlador {

    private final TipoVehiculoService tipoVehiculoService;

    /**
     * Endpoint para obtener todos los tipos de vehículos.
     * @return Lista de tipos de vehículos
     */
    @Operation(
            summary = "Listar diferentes categorias de vehiculos",
            description = "Al llamar a este endpoint, nos traeremos la lista de los diferentes tipos de vehiculos que existen en la base de datos",
            tags = "Vehiculos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han obtenido la lista.",
                    content = @Content (
                            mediaType ="application/json",
                            schema = @Schema(implementation = TipoVehiculoResponseDTO.class),
                            examples = @ExampleObject(value =
                                    """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Camion"
                                              },
                                              {
                                                "id": 2,
                                                "nombre": "Furgoneta"
                                              },
                                              {
                                                "id": 3,
                                                "nombre": "Coche"
                                              },
                                              {
                                                "id": 4,
                                                "nombre": "Autobus"
                                              },
                                              {
                                                "id": 5,
                                                "nombre": "Caravana"
                                              }
                                            ]
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No hay tipos de vehiculos.",
                    content = @Content(mediaType = "application/json")

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/lista")
    public ResponseEntity<List<TipoVehiculoResponseDTO>> listarTiposVehiculo() {
        return ResponseEntity.ok(tipoVehiculoService.getAll());
    }

    /**
     * Endpoint para obtener un tipo de vehículo por su ID.
     *
     * @param id ID del tipo de vehículo
     * @return TipoVehiculoResponseDTO
     */

    @Operation(
            summary = "Obtiene un vehiculo por id",
            description = "Obtiene una categoria buscada por id",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "vehiculo editado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TipoVehiculoResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                              {
                                                "id": 5,
                                                "marca": "camion-retractil",
                                              }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No hay tipos de vehiculo",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTipoVehiculoPorId(@PathVariable Integer id) {
        try {
            TipoVehiculoResponseDTO tipoVehiculo = tipoVehiculoService.getTipoVehiculoPorId(id);
            return ResponseEntity.ok(tipoVehiculo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el Tipo de Vehículo para el ID: " + id);
        }
    }

    /**
     * Endpoint para guardar un nuevo tipo de vehículo.
     * @param requestDTO Datos del tipo de vehículo a guardar
     * @return TipoVehiculoResponseDTO con los datos del tipo de vehículo guardado
     */
    @Operation(
            summary = "Guardar un nuevo tipo de vehiculo",
            description = "Crea una nueva categoria con los datos proporcionados.",
            tags = {"Vehiculos"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria de vehiculo guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "id": 5,
                                                "nombre": "Camion retractil"
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el almacén",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/guardar")
    public ResponseEntity<TipoVehiculoResponseDTO> guardarTipoVehiculo(@RequestBody TipoVehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(tipoVehiculoService.guardar(requestDTO));
    }

//    @Operation(
//            summary = "Edita tipo de vehiculo por ID",
//            description = "Devuelve un tipo de vehiculo basado en su ID proporcionado.",
//            tags = {"Vehiculos"}
//    )
//    @ApiResponses(
//            value = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Tipo de vehiculo modificado con exito",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = ProvinciaDTO.class),
//                                    examples = @ExampleObject(value = """
//                                            {
//                                              {
//                                                "id": 5,
//                                                "nombre": "Camion retractil"
//                                              }
//                                            """)
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "Tipo de vehiculo no encontrado",
//                            content = @Content(mediaType = "application/json")
//                    ),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Error interno del servidor",
//                            content = @Content(mediaType = "application/json")
//                    )
//            }
//    )
//    @PutMapping("/editar/{id}")
//    public ResponseEntity<TipoVehiculoResponseDTO> editarTipoVehiculo(@PathVariable Integer id, @RequestBody TipoVehiculoRequestDTO requestDTO) {
//        return ResponseEntity.ok(tipoVehiculoService.editar(id, requestDTO));
//    }

//    /**
//     * Endpoint para eliminar un tipo de vehículo.
//     * @param id ID del tipo de vehículo a eliminar
//     */
//
//    @Operation(
//            summary = "Eliminar una categoria de vehiculo",
//            description = "Elimina una categoria existente por su ID.",
//            tags = {"Admin"}
//    )
//    @ApiResponses(
//            value = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Categoria eliminada con éxito.",
//                            content = @Content(mediaType = "application/json")
//                    ),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "Categoria no encontrada.",
//                            content = @Content(mediaType = "application/json")
//                    ),
//                    @ApiResponse(
//                            responseCode = "403",
//                            description = "El usuario no está autorizado para realizar está funcion.",
//                            content = @Content(mediaType = "application/json")
//                    ),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Error interno del servidor",
//                            content = @Content(mediaType = "application/json")
//                    )
//            }
//    )
//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<Response> eliminarTipoVehiculo(@PathVariable Integer id) {
//        Response respuesta = tipoVehiculoService.eliminar(id);
//        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
//    }
}
