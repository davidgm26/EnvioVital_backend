package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.ConductorResponseDTO;
import com.safa.enviovital.dto.VehiculoRequestDTO;
import com.safa.enviovital.dto.VehiculoResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Vehiculo;
import com.safa.enviovital.servicios.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@AllArgsConstructor
public class VehiculoControlador {

    @Autowired
    private final VehiculoService vehiculoService;

    @Operation(
            summary = "Listar todos los vehiculos",
            description = "Trae una lista de todos los vehiculos con los datos proporcionados.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "vehiculo guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VehiculoResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            [
                                              {
                                                "id": 1,
                                                "marca": "Ford",
                                                "modelo": "Transit",
                                                "matricula": "ABC123A",
                                                "idConductor": 1,
                                                "idTipoVehiculo": 1
                                              },
                                              {
                                                "id": 2,
                                                "marca": "Mercedes",
                                                "modelo": "Sprinter",
                                                "matricula": "ABC123B",
                                                "idConductor": 2,
                                                "idTipoVehiculo": 1
                                              },
                                              {
                                                "id": 3,
                                                "marca": "Opel",
                                                "modelo": "Astra",
                                                "matricula": "ABC123C",
                                                "idConductor": 3,
                                                "idTipoVehiculo": 2
                                              },
                                            ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado vehiculos.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/lista")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.getAll());
    }

    @Operation(
            summary = "Obtener un vehiculo por id",
            description = "Obtiene un  vehiculo buscado por su id.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "vehiculo editado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VehiculoResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                              {
                                                "id": 10,
                                                "marca": "Nissan",
                                                "modelo": "NV200",
                                                "matricula": "ABC123K",
                                                "idConductor": 5,
                                                "idTipoVehiculo": 1
                                              }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el vehiculo",
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
    public ResponseEntity<VehiculoResponseDTO> obtenerVehiculoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.getVehiculoPorId(id));
    }


    @Operation(
            summary = "Guardar un nuevo vehiculo",
            description = "Crea un nuevo vehiculo con los datos proporcionados.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "vehiculo guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VehiculoResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                              {
                                                "id": 10,
                                                "marca": "Nissan",
                                                "modelo": "NV200",
                                                "matricula": "ABC123K",
                                                "idConductor": 5,
                                                "idTipoVehiculo": 1
                                              }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el vehiculo",
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
    public ResponseEntity<VehiculoResponseDTO> guardarVehiculo(@RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.guardar(requestDTO));
    }

    @Operation(
            summary = "Editar un vehiculo",
            description = "Edita un  vehiculo buscado por su id con los datos proporcionados.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "vehiculo editado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VehiculoResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                              {
                                                "id": 10,
                                                "marca": "Nissan",
                                                "modelo": "NV200",
                                                "matricula": "ABC123K",
                                                "idConductor": 5,
                                                "idTipoVehiculo": 1
                                              }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el vehiculo",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping("/editar/{id}")
    public ResponseEntity<VehiculoResponseDTO> editarVehiculo(@PathVariable Integer id, @RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.editar(id, requestDTO));
    }

    @Operation(
            summary = "Eliminar un vehiculo",
            description = "Elimina un vehiculo existente por su ID.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vehiculo eliminado con éxito.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vehiculo no encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "El usuario no está autorizado para realizar está funcion.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarVehiculo(@PathVariable Integer id) {
        Response respuesta = vehiculoService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}