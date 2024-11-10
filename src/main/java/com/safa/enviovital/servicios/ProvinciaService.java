package com.safa.enviovital.servicios;

import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.repositorios.ProvinciaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinciaService {

    private ProvinciaRepositorio provinciaRepositorio;

    /**
     * Metodo que retorna todos los almacenes
     * @return
     */
    public List<Provincia> getAll(){
        return provinciaRepositorio.findAll();
    }

    /**
     * Metodo que guarda un almacen
     * @param provincia
     * @return
     */
    public Provincia guardar(Provincia provincia){
        return provinciaRepositorio.save(provincia);
    }

    /**
     * Metodo que elimina un almacen
     * @param id
     */
    public void eliminar(Integer id) {
        Provincia provincia = provinciaRepositorio.findById(id).orElse(null);

        if(provincia == null){
            throw new RuntimeException("El almacen con id: "+id+" no existe");
        }
        try {
            provinciaRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El almacen con id: "+id+" no puede ser eliminado");
        }
    }

    /**
     * Metodo que actualiza un almacen
     * @param  provincia
     * @return
     */
    public Provincia actualizar(Provincia provincia) {
        return provinciaRepositorio.save(provincia);
    }

    /**
     * Metodo que retorna un almacen por su id
     * @param id
     * @return
     */
    public Provincia getAlmacenPorId(Integer id) {
        return provinciaRepositorio.findById(id).orElse(null);
    }
}
