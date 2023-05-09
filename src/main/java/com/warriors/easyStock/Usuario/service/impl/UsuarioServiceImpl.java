package com.warriors.easyStock.Usuario.service.impl;

import com.warriors.easyStock.Usuario.dto.UsuarioNuevoDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.repository.IUsuarioRepository;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.roles.entities.Rol;
import com.warriors.easyStock.roles.enums.RolNombre;
import com.warriors.easyStock.roles.service.IRolService;
import com.warriors.easyStock.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepository;
    IRolService rolService;

    PasswordEncoder passwordEncoder;

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
    public Usuario crearUsuario(UsuarioNuevoDTO us) {
        Usuario usuario =
                new Usuario(us.getNombre(), us.getTelefono(), us.getDireccion(),
                        us.getCiudad(), us.getUsuario(), passwordEncoder.encode(us.getContrasena()),
                        us.getEstado(), us.getCorreo(),us.getDocumento());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USUARIO).get());
        if (us.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        if (us.getRoles().contains("vendedor"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_VENDEDOR).get());
        if (us.getRoles().contains("cliente"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editarUsuarios(int id, UsuarioNuevoDTO usuario) {
        Usuario usuarioBD = usuarioRepository.findById(id).orElse(null);
        if (usuarioBD != null) {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setUsuario(usuario.getUsuario());
            usuarioBD.setCorreo(usuario.getCorreo());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setCiudad(usuario.getCiudad());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            usuarioRepository.save(usuarioBD);

            return usuarioBD;
        }
        throw new NotFoundException("El usuario con id "+id+" "+"No existe");
    }

    @Override
    public Usuario eliminarUsuarios(int id) {
        Usuario usuarioBD = usuarioRepository.findById(id).orElse(null);
        if (usuarioBD != null) {
            usuarioRepository.deleteById(id);
            return usuarioBD;
        } else {
            throw new NotFoundException("El usuario con id "+id+" "+"No existe");
        }

    }

    @Override
    public Optional<Usuario> findByTokenPassword(String tokenPassword) {
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

}
