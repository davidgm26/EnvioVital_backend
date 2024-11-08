package com.safa.enviovital.servicios;

import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.repositorios.AlmacenRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlmacenService {

    private AlmacenRepositorio almacenRepositorio;

    /**
     * Metodo que retorna todos los almacenes
     * @return
     */
    public List<Almacen> getAll(){
        return almacenRepositorio.findAll();
    }

    /**
     * Metodo que guarda un almacen
     * @param almacen
     * @return
     */
    public Almacen guardar(Almacen almacen){
        return almacenRepositorio.save(almacen);
    }

    /**
     * Metodo que elimina un almacen
     * @param id
     */
    public void eliminar(Integer id) {
        Almacen almacen = almacenRepositorio.findById(id).orElse(null);

        if(almacen == null){
            throw new RuntimeException("El almacen con id: "+id+" no existe");
        }
        try {
            almacenRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El almacen con id: "+id+" no puede ser eliminado");
        }
    }

    /**
     * Metodo que actualiza un almacen
     * @param almacen
     * @return
     */
    public Almacen actualizar(Almacen almacen) {
        return almacenRepositorio.save(almacen);
    }

    /**
     * Metodo que retorna un almacen por su id
     * @param id
     * @return
     */
    public Almacen getAlmacenPorId(Integer id) {
        return almacenRepositorio.findById(id).orElse(null);
    }
}
