package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.dto.ProvinciaDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.servicios.ProvinciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@AllArgsConstructor
public class ProvinciaControlador {

    private final ProvinciaService provinciaService;

    /**
     * Endpoint para obtener todas las provincias.
     * @return Lista de provincias
     */
    @Operation(
            summary = "Listar todas las provincias",
            description = "Trae una lista con todas los provincias guardados en la base de datos.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProvinciaDTO.class),
                                    examples = @ExampleObject(value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Álava"
                                              },
                                              {
                                                "id": 2,
                                                "nombre": "Albacete"
                                              },
                                              {
                                                "id": 3,
                                                "nombre": "Alicante"
                                              },
                                              {
                                                "id": 4,
                                                "nombre": "Almería"
                                              },
                                              {
                                                "id": 5,
                                                "nombre": "Asturias"
                                              },
                                              {
                                                "id": 6,
                                                "nombre": "Ávila"
                                              },
                                              {
                                                "id": 7,
                                                "nombre": "Badajoz"
                                              }
                                            ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado provincias.",
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
    public ResponseEntity<List<ProvinciaDTO>> listarProvincias() {
        return ResponseEntity.ok(provinciaService.getAll());
    }

    /**
     * Endpoint para obtener una provincia por su ID.
     * @param id ID de la provincia
     * @return ProvinciaDTO
     */

    @Operation(
            summary = "Obtener provincia por ID",
            description = "Devuelve un almacén basado en su ID proporcionado.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "provincia encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProvinciaDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
                                              "id": 41,
                                              "nombre": "Soria"
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provincia no encontrada",
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
    public ResponseEntity<ProvinciaDTO> obtenerProvinciaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(provinciaService.getProvinciaPorId(id));
    }
        }
