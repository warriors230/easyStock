package com.warriors.easyStock.Usuario.service;

import com.warriors.easyStock.Usuario.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> ListarUsuarios();
    public Usuario crearUsuario(Usuario usuario);

    public Usuario editarUsuarios(int id, Usuario usuario);
    public Usuario eliminarUsuarios(int id);

}
