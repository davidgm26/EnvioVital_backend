package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.ConductorService;
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
@RequestMapping("/conductores")
@AllArgsConstructor
public class ConductorControlador {

    private final ConductorService conductorService;


    /**
     * Endpoint para obtener todos los conductores.
     *
     * @return Lista de conductores
     */

    @Operation(
            summary = "Listar todos los Conductores",
            description = "Trae una lista con todos los conductores guardados en la base de datos.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            [
                                               {
                                                "id": 1,
                                                "nombre": "Antonio",
                                                "apellidos": "García López",
                                                "dni": "12345678A",
                                                "direccion": "Calle Gran Vía, Madrid",
                                                "telefono": "612345678",
                                                "fechaNacimiento": "1980-05-15",
                                                "email": "antonio.garcia@example.es",
                                                "idUsuario": 2,
                                                "activo": true
                                            
                                                },
                                                {
                                            
                                                "id": 2,
                                                "nombre": "María",
                                                "apellidos": "Martínez Sánchez",
                                                "dni": "12345679B",
                                                "direccion": "Calle Alcalá, Madrid",
                                                "telefono": "622345678",
                                                "fechaNacimiento": "1985-08-22",
                                                "email": "maria.martinez@example.es",
                                                "idUsuario": 3,
                                                "activo": true
                                            
                                                },
                                                {
                                                "id": 3,
                                                "nombre": "Carlos",
                                                "apellidos": "Rodríguez Gómez",
                                                "dni": "12345680C",
                                                "direccion": "Avenida Diagonal, Barcelona",
                                                "telefono": "632345678",
                                                "fechaNacimiento": "1990-01-30",
                                                "email": "carlos.rodriguez@example.es",
                                                "idUsuario": 4,
                                                "activo": true
                                                }
                                            ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado conductores",
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
    public ResponseEntity<List<ConductorResponseDTO>> listarConductores() {
        return ResponseEntity.ok(conductorService.getAll());
    }

    /**
     * Endpoint para obtener un conductor por su ID.
     *
     * @param id ID del conductor
     * @return ConductorResponseDTO
     */
    @Operation(
            summary = "Obtener Conductor por ID",
            description = "Devuelve un conductor basado en su ID proporcionado.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                                                                      {
                                            "id": 5,
                                            "nombre": "Pedro",
                                            "apellidos": "López Fernández",
                                            "dni": "12345682E",
                                            "direccion": "Paseo de Gracia, Barcelona",
                                            "telefono": "652345678",
                                            "fechaNacimiento": "1982-11-25",
                                            "email": "pedro.lopez@example.es",
                                            "idUsuario": 6,
                                            "activo": true
                                                                                      }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado",
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
    public ResponseEntity<ConductorResponseDTO> obtenerConductorPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(conductorService.getConductorPorId(id));
    }


    @Operation(
            summary = "Obtener Conductor por ID de usuario",
            description = "Devuelve un conductor basado en el id de su usuario.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                                                                      {
                                            "id": 5,
                                            "nombre": "Pedro",
                                            "apellidos": "López Fernández",
                                            "dni": "12345682E",
                                            "direccion": "Paseo de Gracia, Barcelona",
                                            "telefono": "652345678",
                                            "fechaNacimiento": "1982-11-25",
                                            "email": "pedro.lopez@example.es",
                                            "idUsuario": 6,
                                            "activo": true
                                                                                      }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado",
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
    public ResponseEntity<ConductorResponseDTO> getConductorByUsuarioId(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(conductorService.getConductorByUsuarioId(idUsuario));
    }

    /**
     * Endpoint para guardar un nuevo conductor.
     *
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */

    @Operation(
            summary = "Guardar un nuevo conductor",
            description = "Crea un nuevo conductor con los datos proporcionados.",
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor guardado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ConductorResponseDTO.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "id": 6,
                                                "nombre": "nombre_4c3d87c61816",
                                                "apellidos": "apellidos_661499d1eb30",
                                                "dni": "11111125y",
                                                "direccion": "direccion_fdf8c83798a5",
                                                "telefono": "600123123",
                                                "fechaNacimiento": "2024-11-09",
                                                "email": "manolito@gmail.com",
                                                "idUsuario": 18,
                                                "activo": true
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para el conductor",
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
    public ResponseEntity<ConductorResponseDTO> guardarConductor(@RequestBody ConductorRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.guardar(requestDTO)));
    }

    /**
     * Endpoint para editar un conductor.
     *
     * @param id         ID del conductor a editar
     * @param requestDTO Datos del conductor a editar
     * @return ConductorResponseDTO con los datos del conductor editado
     */

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
                            description = "Conductor no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la edición del conductor",
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
    public ResponseEntity<ConductorResponseDTO> editarConductor(@PathVariable Integer id, @RequestBody ConductorRequestDTO requestDTO) {
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.editar(id, requestDTO)));
    }

    /**
     * Endpoint para eliminar un conductor.
     *
     * @param id ID del conductor a eliminar
     */


    @Operation(
            summary = "Eliminar un conductor",
            description = "Elimina un conductor existente por su ID.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor eliminado con éxito.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado.",
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
    public ResponseEntity<?> eliminarConductor(@PathVariable Integer id) {
        conductorService.borrarConductor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para registrar un conductor en un EventoAlmacen.
     *
     * @param eventoAlmacenId ID del EventoAlmacen
     * @param conductorId     ID del conductor
     * @return ResponseEntity con el mensaje de éxito o error
     */

    @Operation(
            summary = "Registrar un conductor en un EventoAlmacen",
            description = """
            Registra un conductor en un EventoAlmacen y lo asigna a un almacén específico. 
            Se validan los IDs proporcionados y el sistema asegura que no exista una inscripción duplicada. 
            Este endpoint es accesible solo para usuarios autenticados con permisos adecuados.
        """,
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Conductor registrado con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventoAlmacenConductorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Los parámetros proporcionados no son válidos o están incompletos.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron el EventoAlmacen, el conductor o el almacén en la base de datos.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "El conductor ya está registrado en este EventoAlmacen.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocurrió un error interno en el servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/registrarse/{eventoAlmacenId}/{conductorId}/{almacenId}")
    public ResponseEntity<EventoAlmacenConductorDto> registrarConductorEnEventoAlmacen(
            @PathVariable Integer eventoAlmacenId,
            @PathVariable Integer conductorId,
            @PathVariable Integer almacenId) {
        return conductorService.registrarConductorEnEventoAlmacen(eventoAlmacenId, conductorId, almacenId);
    }

    @Operation(
            summary = "Listar almacenes registrados por un conductor",
            description = """
            Devuelve una lista de los almacenes en los que un conductor está registrado.
            Requiere el ID del conductor como parámetro. Si el conductor no está registrado en ningún almacén,
            se devuelve una lista vacía.
        """,
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de almacenes obtenida con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ListaAlmacenesRegistradosByConductorDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID del conductor inválido o mal formado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró ningún conductor con el ID proporcionado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/almacenesRegistrados/{conductorId}")
    public ResponseEntity<List<ListaAlmacenesRegistradosByConductorDTO>> listarAlmacenesRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.obtenerEventoAlmacenPorConductor(conductorId));
    }

    @Operation(
            summary = "Eliminar la inscripcion de un conductor en un EventoAlmacen",
            description = """
            Elimina el registro de un conductor en un EventoAlmacen especificado por su ID.
            Este proceso es irreversible y requiere autorización previa. 
            Si el registro no existe, se devuelve un error.
        """,
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro eliminado con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID del registro inválido o mal formado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró el registro con el ID proporcionado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No autorizado para realizar esta operación.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/eliminarRegistro/{eventoAlmacenConductorId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenConductorId) {
        return ResponseEntity.ok(conductorService.eliminarRegistroConductorEnEventoAlmacen(eventoAlmacenConductorId));
    }


    @Operation(
            summary = "Listar vehículos registrados por un conductor",
            description = """
            Devuelve una lista de los vehículos asociados a un conductor específico. 
            Se requiere el ID del conductor como parámetro. Si el conductor no tiene vehículos registrados, 
            se devuelve una lista vacía.
        """,
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de vehículos obtenida con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VehiculoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID del conductor inválido o mal formado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró ningún conductor con el ID proporcionado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    @GetMapping("/vehiculosRegistrados/{conductorId}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculosRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.getVehiculosByConductorId(conductorId));
    }


    @Operation(
            summary = "Cambiar el estado de un conductor",
            description = """
            Cambia el estado de un conductor específico. 
            Se requiere el ID del conductor como parámetro. 
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
    private ResponseEntity<ConductorResponseDTO> changeConductorState(@PathVariable int id) {
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.cambiarEstadoConductor(id)));
    }


    @Operation(
            summary = "Comprobar la inscripción de un conductor en un EventoAlmacen",
            description = """
            Este endpoint permite comprobar si un conductor está inscrito en un EventoAlmacen específico.
            Requiere los IDs del EventoAlmacen, del conductor y del almacén.
            Devuelve `true` si el conductor está inscrito, `false` en caso contrario.
        """,
            tags = {"Conductores"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inscripción comprobada con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID de uno o más parámetros inválido o mal formado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró el evento, conductor o almacén con los IDs proporcionados.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/inscripcion/{idEventoAlmacen}/{idConductor}/{idAlmacen}")
    private ResponseEntity<Boolean> comprobarInscripcion(@PathVariable int idEventoAlmacen, @PathVariable int idConductor, @PathVariable int idAlmacen) {
        return ResponseEntity.ok(conductorService.conductorInscritoEnEventoAlmacen(idEventoAlmacen, idConductor, idAlmacen));
    }


}
