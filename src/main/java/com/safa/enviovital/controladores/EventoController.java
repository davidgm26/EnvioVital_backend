package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.dto.ConductorResponseDTO;
import com.safa.enviovital.dto.EventoRequestDto;
import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.servicios.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
@AllArgsConstructor
@Tag(name = "Eventos", description = "Endpoint para gestionar todo lo relacionado con los eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Operation(
            summary = "Listar todos los eventos",
            description = "Trae una lista con todos los eventos guardados en la base de datos.",
            tags = {"Eventos"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventoResponseDto.class),
                                    examples = @ExampleObject(value = """
                                            [
                                               {
                                                 "id": 1,
                                                 "nombre": "Catástrofe Madrid",
                                                 "descripcion": "Inundación por fuertes lluvias.",
                                                 "activo": true,
                                                 "provincia": "Madrid",
                                                 "almacenes": [
                                                   {
                                                     "id": 1,
                                                     "idEvento": 1,
                                                     "idAlmacen": 1,
                                                     "nombreAlmacen": "Almacén Central Madrid",
                                                     "nombreEvento": "Catástrofe Madrid",
                                                     "almacen": {
                                                       "id": 1,
                                                       "nombre": "Almacén Central Madrid",
                                                       "descripcion": "Almacén principal de distribución en Madrid",
                                                       "direccion": "Calle Mayor, 10, 28013 Madrid",
                                                       "email": "central.madrid@example.es",
                                                       "esActivo": true,
                                                       "provincia": "Madrid",
                                                       "idUsuario": 7,
                                                       "fotoUrl": null
                                                     }
                                                   }
                                                 ]
                                               },
                                               {
                                                 "id": 4,
                                                 "nombre": "Catástrofe Sevilla",
                                                 "descripcion": "Severa ola de calor.",
                                                 "activo": false,
                                                 "provincia": "Sevilla",
                                                 "almacenes": [
                                                   {
                                                     "id": 4,
                                                     "idEvento": 4,
                                                     "idAlmacen": 4,
                                                     "nombreAlmacen": "Depósito Sevilla",
                                                     "nombreEvento": "Catástrofe Sevilla",
                                                     "almacen": {
                                                       "id": 4,
                                                       "nombre": "Depósito Sevilla",
                                                       "descripcion": "Depósito regional para Andalucía",
                                                       "direccion": "Avenida de la Constitución, 1, 41001 Sevilla",
                                                       "email": "deposito.sevilla@example.es",
                                                       "esActivo": false,
                                                       "provincia": "Sevilla",
                                                       "idUsuario": 10,
                                                       "fotoUrl": null
                                                     }
                                                   },
                                                   {
                                                     "id": 15,
                                                     "idEvento": 4,
                                                     "idAlmacen": 2,
                                                     "nombreAlmacen": "Almacén Norte Barcelona",
                                                     "nombreEvento": "Catástrofe Sevilla",
                                                     "almacen": {
                                                       "id": 2,
                                                       "nombre": "Almacén Norte Barcelona",
                                                       "descripcion": "Almacén regional para Cataluña",
                                                       "direccion": "Avenida Diagonal, 250, 08013 Barcelona",
                                                       "email": "norte.barcelona@example.es",
                                                       "esActivo": false,
                                                       "provincia": "Barcelona",
                                                       "idUsuario": 8,
                                                       "fotoUrl": null
                                                     }
                                                   }
                                                 ]
                                               },
                                             ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado eventos",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/")
    private ResponseEntity<List<EventoResponseDto>> getAllEventos(){
     return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @Operation(
            summary = "Listar todos los eventos activos",
            description = "Trae una lista con todos los eventos que se encuentran en estado activo y guardados en la base de datos.",
            tags = {"Eventos"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventoResponseDto.class),
                                    examples = @ExampleObject(value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Catástrofe Madrid",
                                                "descripcion": "Inundación por fuertes lluvias.",
                                                "activo": true,
                                                "provincia": "Madrid",
                                                "almacenes": [
                                                  {
                                                    "id": 1,
                                                    "idEvento": 1,
                                                    "idAlmacen": 1,
                                                    "nombreAlmacen": "Almacén Central Madrid",
                                                    "nombreEvento": "Catástrofe Madrid",
                                                    "almacen": {
                                                      "id": 1,
                                                      "nombre": "Almacén Central Madrid",
                                                      "descripcion": "Almacén principal de distribución en Madrid",
                                                      "direccion": "Calle Mayor, 10, 28013 Madrid",
                                                      "email": "central.madrid@example.es",
                                                      "esActivo": true,
                                                      "provincia": "Madrid",
                                                      "idUsuario": 7,
                                                      "fotoUrl": null
                                                    }
                                                  }
                                                ]
                                              },
                                              {
                                                "id": 3,
                                                "nombre": "Catástrofe Valencia",
                                                "descripcion": "Tornado en la zona urbana.",
                                                "activo": true,
                                                "provincia": "Valencia",
                                                "almacenes": [
                                                  {
                                                    "id": 3,
                                                    "idEvento": 3,
                                                    "idAlmacen": 3,
                                                    "nombreAlmacen": "Centro Logístico Valencia",
                                                    "nombreEvento": "Catástrofe Valencia",
                                                    "almacen": {
                                                      "id": 3,
                                                      "nombre": "Centro Logístico Valencia",
                                                      "descripcion": "Centro de operaciones en Valencia",
                                                      "direccion": "Calle Colón, 45, 46004 Valencia",
                                                      "email": "logistico.valencia@example.es",
                                                      "esActivo": true,
                                                      "provincia": "Valencia",
                                                      "idUsuario": 9,
                                                      "fotoUrl": null
                                                    }
                                                  }
                                                ]
                                              },
                                            ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado eventos",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/activos")
    private ResponseEntity<List<EventoResponseDto>> getAllEventosActivos(){
        return ResponseEntity.ok(eventoService.getAllActivos());
    }
    @Operation(
            summary = "Obtener Evento por ID de usuario",
            description = "Devuelve un evento basado en su id.",
            tags = {"Eventos"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Evento encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventoResponseDto.class),
                                    examples = @ExampleObject(value = """
                                    {
                                      "id": 3,
                                      "nombre": "Catástrofe Valencia",
                                      "descripcion": "Tornado en la zona urbana.",
                                      "activo": true,
                                      "provincia": "Valencia",
                                      "almacenes": [
                                        {
                                          "id": 3,
                                          "idEvento": 3,
                                          "idAlmacen": 3,
                                          "nombreAlmacen": "Centro Logístico Valencia",
                                          "nombreEvento": "Catástrofe Valencia",
                                          "almacen": {
                                            "id": 3,
                                            "nombre": "Centro Logístico Valencia",
                                            "descripcion": "Centro de operaciones en Valencia",
                                            "direccion": "Calle Colón, 45, 46004 Valencia",
                                            "email": "logistico.valencia@example.es",
                                            "esActivo": true,
                                            "provincia": "Valencia",
                                            "idUsuario": 9,
                                            "fotoUrl": null
                                          }
                                        }
                                      ]
                                    }
                                    """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Evento no encontrado",
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
    private ResponseEntity<EventoResponseDto> getEventoById(@PathVariable int id){
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.getEventoById(id)));
    }

    @GetMapping("/provincia/{id}")
    private ResponseEntity<List<EventoResponseDto>> getEventosByProvincia(@PathVariable int id){
        return ResponseEntity.ok(eventoService.getEventoByProvincia(id));
    }

    @Operation(
            summary = "Guardar un nuevo evento",
            description = "Crea un nuevo evento con los datos proporcionados.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Evento guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "id": 16,
                                                "nombre": "Terremoto de Lisboa, daños en Sevilla",
                                                "descripcion": "Un gran terremoto que ha afectado a Sevilla",
                                                "esActivo": true,
                                                "fotoUrl": null
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el evento",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/")
    private ResponseEntity<Evento> createEvento(@RequestBody EventoRequestDto eventoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.createEvento(eventoRequest));
    }

    @Operation(
            summary = "Editar un conductor",
            description = "Edita un conductor existente con los datos proporcionados.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor editado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Evento no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la edición del evento",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping("/{id}")
    private ResponseEntity<EventoResponseDto> editarEvento(@PathVariable int id,@RequestBody EventoRequestDto eventoRequest){
        return ResponseEntity.ok(eventoService.editarEvento(id,eventoRequest));
    }
    @Operation(
            summary = "Eliminar un evento",
            description = "Elimina un evento existente por su ID.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Evento eliminado con éxito.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Evento no encontrado.",
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
    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarEvento(@PathVariable int id){
        eventoService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Cambiar el estado de un evento",
            description = """
            Cambia el estado de un evento específico. 
            Se requiere el ID del evento como parámetro. 
            """,
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Estado del conductor cambiado con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID del conductor inválido o solicitud mal formada.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No tiene permiso para realizar esta funcion.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró el conductor con el ID proporcionado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping("/estado/{id}")
    private ResponseEntity<EventoResponseDto> changeEventoStatus(@PathVariable int id){
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.cambiarEstadoEvento(id)));
    }








}
