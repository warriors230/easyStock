package com.warriors.easyStock.Usuario.service;

import com.warriors.easyStock.Security.dto.NuevoUsuario;
import com.warriors.easyStock.Usuario.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> ListarUsuarios();
    public Usuario crearUsuario(NuevoUsuario usuario);

    public Usuario editarUsuarios(int id, Usuario usuario);
    public Usuario eliminarUsuarios(int id);

    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String correo);

}
