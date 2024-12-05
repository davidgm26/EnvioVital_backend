package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.AlmacenNameAlredyExistsException;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.servicios.AlmacenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almacenes")
@AllArgsConstructor
@Tag(name = "Almacenes", description = "Endpoint para gestionar todo lo relacionado con los almacenes ")
public class AlmacenControlador {

    @Autowired
    private final AlmacenService almacenService;

    /**
     * Endpoint para obtener todos los almacenes.
     *
     * @return Lista de almacenes
     */

    @Operation(
            summary = "Listar todos los almacenes",
            description = "Trae una lista con todos los almacenes guardados en la base de datos.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            [
                                                {
                                                    "id": 2,
                                                    "nombre": "Almacén Norte Barcelona",
                                                    "descripcion": "Almacén regional para Cataluña",
                                                    "direccion": "Avenida Diagonal, 250, 08013 Barcelona",
                                                    "email": "norte.barcelona@example.es",
                                                    "esActivo": false,
                                                    "provincia": "Barcelona",
                                                    "idUsuario": 8,
                                                    "fotoUrl": null
                                                },
                                                {
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
                                            ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado almacenes",
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
    public ResponseEntity<List<AlmacenResponseDTO>> listarAlmacenes() {
        return ResponseEntity.ok(almacenService.getAll());
    }

    /**
     * Endpoint para obtener un almacén por su ID.
     *
     * @param id ID del almacén
     * @return AlmacenResponseDTO
     */

    @Operation(
            summary = "Obtener almacén por ID",
            description = "Devuelve un almacén basado en su ID proporcionado.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
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
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado",
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
    public ResponseEntity<AlmacenResponseDTO> obtenerAlmacenPorId(@PathVariable Integer id) {
        Almacen a = almacenService.getAlmacenPorId(id);
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(a));
    }


    @Operation(
            summary = "Obtener almacén por ID de usuario",
            description = "Devuelve un almacén asociado a un usuario específico.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
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
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado para el usuario",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<AlmacenResponseDTO> getAlmacenByUsuarioId(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(almacenService.getAlmacenByUsuarioId(idUsuario));
    }

    /**
     * Endpoint para guardar un nuevo almacén.
     *
     * @param requestDTO Datos del almacén a guardar
     * @return AlmacenResponseDTO con los datos del almacén guardado
     */
    @Operation(
            summary = "Guardar un nuevo almacén",
            description = "Crea un nuevo almacén con los datos proporcionados.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
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
    public ResponseEntity<AlmacenResponseDTO> guardarAlmacen(@RequestBody AlmacenRequestDTO requestDTO) throws AlmacenNameAlredyExistsException, UsernameAlredyExistsException {
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(almacenService.guardar(requestDTO)));
    }

    /**
     * Endpoint para editar un almacén.
     *
     * @param id         ID del almacén a editar
     * @param requestDTO Datos del almacén a editar
     * @return AlmacenResponseDTO con los datos del almacén editado
     */
    @Operation(
            summary = "Editar un almacén",
            description = "Edita un almacén existente con los datos proporcionados.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén editado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la edición del almacén",
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
    public ResponseEntity<AlmacenResponseDTO> editarAlmacen(@PathVariable Integer id, @RequestBody AlmacenEditarDTO requestDTO) {
        return ResponseEntity.ok(almacenService.editar(id, requestDTO));
    }

    /**
     * Endpoint para eliminar un almacén.
     *
     * @param id ID del almacén a eliminar
     * @return Response con el mensaje de eliminación
     */
    @Operation(
            summary = "Eliminar un almacén",
            description = "Elimina un almacén existente por su ID.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén eliminado con éxito.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado.",
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
    public ResponseEntity<Response> eliminarAlmacen(@PathVariable Integer id) {
        Response respuesta = almacenService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }

    /**
     * Endpoint para registrar un almacén en un evento.
     *
     * @param idEvento  ID del evento
     * @param idAlmacen ID del almacén
     * @return ResponseEntity con el mensaje de éxito
     */
    @Operation(
            summary = "Registrar un almacén en un evento",
            description = "Asocia un almacén a un evento.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Almacén registrado en el evento",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Evento o almacén no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/registrarse/{idEvento}/{idAlmacen}")
    public ResponseEntity<Response> registrarAlmacenEnEvento(@PathVariable Integer idEvento, @PathVariable Integer idAlmacen) {
        return almacenService.registrarAlmacenEnEvento(idEvento, idAlmacen);
    }

    @Operation(
            summary = "Obtener almacenes registrados en un evento",
            description = "Devuelve la lista de almacenes que están registrados en un evento específico según el ID del evento.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de almacenes obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventoAlmacenDtoResponse.class),
                                    examples = @ExampleObject(value = """
                                        [
                                            {
                                                "idEvento": 1,
                                                "nombreEvento": "Evento Anual de Logística",
                                                "idAlmacen": 3,
                                                "nombreAlmacen": "Almacén Central Madrid",
                                                "direccion": "Calle Mayor, 10, 28013 Madrid"
                                            },
                                            {
                                                "idEvento": 1,
                                                "nombreEvento": "Evento Anual de Logística",
                                                "idAlmacen": 5,
                                                "nombreAlmacen": "Almacén Norte Barcelona",
                                                "direccion": "Avenida Diagonal, 250, 08013 Barcelona"
                                            }
                                        ]
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
    @GetMapping("/listaregistrados/{idEvento}")
    public ResponseEntity<List<EventoAlmacenDtoResponse>> obtenerAlmacenesPorEvento(@PathVariable Integer idEvento) {
        List<EventoAlmacenDtoResponse> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorEvento(idEvento);
        return ResponseEntity.ok(eventoAlmacenes);
    }

    @Operation(
            summary = "Obtener eventos asociados a un almacén",
            description = "Devuelve los eventos en los que un almacén está registrado.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de eventos obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ListaEventosByAlmacenDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/listaEventos/{idAlmacen}")
    public ResponseEntity<List<ListaEventosByAlmacenDTO>> obtenerEventosPorAlmacen(@PathVariable Integer idAlmacen) {
        List<ListaEventosByAlmacenDTO> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorAlmacen(idAlmacen);
        return ResponseEntity.ok(eventoAlmacenes);
    }


    @Operation(
            summary = "Eliminar un almacén de un evento",
            description = "Elimina el registro de un almacén en un evento específico.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro de almacén en evento eliminado con éxito",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Registro no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/eliminarRegistro/{eventoAlmacenId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenId) {
        return ResponseEntity.ok(almacenService.eliminarRegistroAlmacenEnEvento(eventoAlmacenId));
    }

    @Operation(
            summary = "Obtener conductores asociados a un almacén",
            description = "Devuelve la lista de conductores asociados a un almacén específico.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de conductores obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/listaConductores/{almacenId}")
    public ResponseEntity<List<ConductorResponseDTO>> obtenerConductoresPorAlmacen(@PathVariable Integer almacenId) {
        List<ConductorResponseDTO> conductores = almacenService.obtenerConductoresPorAlmacen(almacenId);
        return ResponseEntity.ok(conductores);
    }

    @Operation(
            summary = "Cambiar el estado de un almacén, funcion disponible solo para admin",
            description = "Permite cambiar el estado de un almacén entre activo o inactivo.",
            tags = {"Almacenes"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Estado del almacén actualizado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AlmacenResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No tiene autorizacion para realizar esta funcion",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Almacén no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping("/estado/{id}")
    public ResponseEntity<AlmacenResponseDTO> changeAlmacenState(@PathVariable Integer id) {
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(almacenService.changeAlmacenState(id)));
    }


}
