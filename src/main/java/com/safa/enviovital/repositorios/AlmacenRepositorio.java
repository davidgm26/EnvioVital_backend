package com.safa.enviovital.repositorios;

import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlmacenRepositorio extends JpaRepository<Almacen, Integer> {

    List<Almacen> findAll();

   Optional<Almacen> findAlmacenById(Integer id);

    @Query("SELECT c FROM Almacen c WHERE c.usuario.id = :idUsuario")
    Optional<Almacen> findAlmacenByUsuarioId(Integer idUsuario);



    @Query("SELECT c FROM Almacen c JOIN c.usuario u ON c.usuario.id = u.id WHERE u.username = :username")
    Optional<Almacen> findAlmacenByUsername(String username);




}
