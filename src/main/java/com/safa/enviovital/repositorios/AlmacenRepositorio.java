package com.safa.enviovital.repositorios;

import com.safa.enviovital.modelos.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlmacenRepositorio extends JpaRepository<Almacen, Integer> {

    List<Almacen> findAll();

   Optional<Almacen> findAlmacenById(Integer id);



}
