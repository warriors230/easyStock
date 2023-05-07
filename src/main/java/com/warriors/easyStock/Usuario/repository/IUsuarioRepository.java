package com.warriors.easyStock.Usuario.repository;

import com.warriors.easyStock.Usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByCorreo(String correo);
}
