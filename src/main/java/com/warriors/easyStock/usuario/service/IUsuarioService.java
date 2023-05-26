package com.warriors.easyStock.usuario.service;

import com.warriors.easyStock.usuario.dto.UsuarioNuevoDTO;
import com.warriors.easyStock.usuario.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario crearUsuario(UsuarioNuevoDTO usuario);

    public Usuario buscarId(int id);

    public Usuario editarUsuarios(int id, UsuarioNuevoDTO usuario);
    public Usuario eliminarUsuarios(int id);

    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String correo);
    boolean existsByUsuario(String usuario);



}
