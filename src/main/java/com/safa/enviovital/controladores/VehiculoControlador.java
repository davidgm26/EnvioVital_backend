package com.safa.enviovital.controladores;


import com.safa.enviovital.modelos.Vehiculo;
import com.safa.enviovital.servicios.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoControlador {
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/lista")
    public List<Vehiculo> getAllAlmacenes() {
        List<Vehiculo> vehiculos = vehiculoService.getAll();

        return vehiculos;
    }
    @GetMapping()
    public Vehiculo getById(@RequestParam Integer id) {
        Vehiculo vehiculo = vehiculoService.getAlmacenPorId(id);
        return vehiculo;
    }
    @PostMapping("/new")
    public Vehiculo guardar(@RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoGuardado = vehiculoService.guardar(vehiculo);
        return vehiculoGuardado;
    }


}
