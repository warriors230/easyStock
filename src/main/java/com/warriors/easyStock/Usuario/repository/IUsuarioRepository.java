package com.warriors.easyStock.Usuario.repository;

import com.warriors.easyStock.Usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Optional <Usuario> findOneByCorreo(String correo);
}
