package com.warriors.easyStock.Usuario.service.impl;

import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.repository.IUsuarioRepository;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> ListarUsuarios() {
        List<Usuario> listaUsuarioBD = usuarioRepository.findAll();
        if (!listaUsuarioBD.isEmpty()) {
            return listaUsuarioBD;
        } else {
            return null;
        }

    }

    @Override
    @Transactional()
    public Usuario crearUsuario(Usuario us) {
        us.setPassword(new BCryptPasswordEncoder().encode(us.getPassword()));
        return usuarioRepository.save(us);
    }

    @Override
    public Usuario editarUsuarios(int id, Usuario usuario) {
        Usuario usuarioBD = usuarioRepository.findById(id).orElse(null);
        if (usuarioBD != null) {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setUser(usuario.getUser());
            usuarioBD.setCorreo(usuario.getCorreo());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setCiudad(usuario.getCiudad());
            usuarioRepository.save(usuarioBD);

            return usuarioBD;
        }
        return null;
    }

    @Override
    public Usuario eliminarUsuarios(int id) {
        Usuario usuarioBD = usuarioRepository.findById(id).orElse(null);
        if (usuarioBD != null) {
            usuarioRepository.deleteById(id);
            return usuarioBD;
        }else{
            return usuarioBD;
        }

    }

}
