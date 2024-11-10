package com.safa.enviovital.controladores;

import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.servicios.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaControlador {
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/lista")
    public List<Provincia> getAllAlmacenes() {
        List<Provincia> provincias = provinciaService.getAll();

        return provincias;
    }
    @GetMapping()
    public Provincia getById(@RequestParam Integer id) {
        Provincia provincia = provinciaService.getProvinciaById(id);
        return provincia;
    }
    @PostMapping("/new")
    public Provincia guardar(@RequestBody Provincia provincia) {
        Provincia provinciaGuardado = provinciaService.guardar(provincia);
        return provinciaGuardado;
    }


}
