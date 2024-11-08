package com.safa.enviovital.controladores;

import com.safa.enviovital.modelos.TipoVehiculo;
import com.safa.enviovital.servicios.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipovehiculo")
public class TipoVehiculoControlador {
    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping("/lista")
    public List<TipoVehiculo> getAllAlmacenes() {
        List<TipoVehiculo> tipoVehiculos = tipoVehiculoService.getAll();

        return tipoVehiculos;
    }
    @GetMapping()
    public TipoVehiculo getById(@RequestParam Integer id) {
        TipoVehiculo tipoVehiculo = tipoVehiculoService.getAlmacenPorId(id);
        return tipoVehiculo;
    }
    @PostMapping("/new")
    public TipoVehiculo guardar(@RequestBody TipoVehiculo tipoVehiculo) {
        TipoVehiculo tipoVehiculoGuardado = tipoVehiculoService.guardar(tipoVehiculo);
        return tipoVehiculoGuardado;
    }


}
