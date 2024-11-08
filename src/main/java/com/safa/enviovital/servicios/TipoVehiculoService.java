package com.safa.enviovital.servicios;

import com.safa.enviovital.modelos.TipoVehiculo;
import com.safa.enviovital.repositorios.TipoVehiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoVehiculoService {

    private TipoVehiculoRepositorio tipoVehiculoRepositorio;

    /**
     * Metodo que retorna todos los almacenes
     * @return
     */
    public List<TipoVehiculo> getAll(){
        return tipoVehiculoRepositorio.findAll();
    }

    /**
     * Metodo que guarda un almacen
     * @param tipoVehiculo
     * @return
     */
    public TipoVehiculo guardar(TipoVehiculo tipoVehiculo){
        return tipoVehiculoRepositorio.save(tipoVehiculo);
    }

    /**
     * Metodo que elimina un almacen
     * @param id
     */
    public void eliminar(Integer id) {
        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findById(id).orElse(null);

        if(tipoVehiculo == null){
            throw new RuntimeException("El almacen con id: "+id+" no existe");
        }
        try {
            tipoVehiculoRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El almacen con id: "+id+" no puede ser eliminado");
        }
    }

    /**
     * Metodo que actualiza un almacen
     * @param  tipoVehiculo
     * @return
     */
    public TipoVehiculo actualizar(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepositorio.save(tipoVehiculo);
    }

    /**
     * Metodo que retorna un almacen por su id
     * @param id
     * @return
     */
    public TipoVehiculo getAlmacenPorId(Integer id) {
        return tipoVehiculoRepositorio.findById(id).orElse(null);
    }
}
