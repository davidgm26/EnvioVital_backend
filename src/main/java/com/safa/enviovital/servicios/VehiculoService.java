package com.safa.enviovital.servicios;



import com.safa.enviovital.modelos.Vehiculo;
import com.safa.enviovital.repositorios.VehiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehiculoService {

    private VehiculoRepositorio vehiculoRepositorio;

    /**
     * Metodo que retorna todos los almacenes
     * @return
     */
    public List<Vehiculo> getAll(){
        return vehiculoRepositorio.findAll();
    }

    /**
     * Metodo que guarda un almacen
     * @param vehiculo
     * @return
     */
    public Vehiculo guardar(Vehiculo vehiculo){
        return vehiculoRepositorio.save(vehiculo);
    }

    /**
     * Metodo que elimina un almacen
     * @param id
     */
    public void eliminar(Integer id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id).orElse(null);

        if(vehiculo == null){
            throw new RuntimeException("El almacen con id: "+id+" no existe");
        }
        try {
            vehiculoRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El almacen con id: "+id+" no puede ser eliminado");
        }
    }

    /**
     * Metodo que actualiza un almacen
     * @param vehiculo
     * @return
     */
    public Vehiculo actualizar(Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }

    /**
     * Metodo que retorna un almacen por su id
     * @param id
     * @return
     */
    public Vehiculo getAlmacenPorId(Integer id) {
        return vehiculoRepositorio.findById(id).orElse(null);
    }
}
