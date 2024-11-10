package com.safa.enviovital.servicios;

import com.safa.enviovital.modelos.Conductor;
import com.safa.enviovital.repositorios.ConductorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConductorService {

    private ConductorRepositorio conductorRepositorio;

    /**
     * Método que se encarga de obtener todos los conductores
     *
     * @return Lista de conductores
     */
    public List<Conductor> getAll() {
        return conductorRepositorio.findAll();
    }

    /**
     * Método que se encarga de guardar un conductor
     * @param conductor Conductor a guardar
     * @return Conductor guardado
     */

    public Conductor guardar(Conductor conductor) {
        return conductorRepositorio.save(conductor);}

    /**
     * Método que se encarga de eliminar un conductor
     * @param id Id del conductor a eliminar
     */

    public void eliminar(Integer id) {
        Conductor conductor = conductorRepositorio.findById(id).orElse(null);

        if (conductor == null) {
            throw new RuntimeException("El conductor con id: " + id + " no existe");
        }
        try {
            conductorRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El conductor con id: " + id + " no puede ser eliminado");
        }

    }

    /**
     * Método que se encarga de actualizar un conductor
     * @param conductor Conductor a actualizar
     * @return Conductor actualizado
     */

    public Conductor actualizar(Conductor conductor) {
        return conductorRepositorio.save(conductor);
    }


}
