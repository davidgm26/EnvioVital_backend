package com.safa.enviovital.controladores;


import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.servicios.AlmacenService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almacen")
public class AlmacenControlador {
    @Autowired
    private AlmacenService almacenService;

    @GetMapping("/lista")
    public List<Almacen> getAllAlmacenes() {
        List<Almacen> almacenes = almacenService.getAll();

        return almacenes;
    }
    @GetMapping()
    public Almacen getById(@RequestParam Integer id) {
        Almacen almacen = almacenService.getAlmacenPorId(id);
        return almacen;
    }
    @PostMapping("/new")
    public Almacen guardar(@RequestBody Almacen almacen) {
        Almacen almacenGuardado = almacenService.guardar(almacen);
        return almacenGuardado;
    }


}
