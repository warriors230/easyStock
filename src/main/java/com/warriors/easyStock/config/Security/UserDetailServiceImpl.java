package com.warriors.easyStock.config.Security;

import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    IUsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findOneByCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("El usuario con correo " + correo + " no existe."));
        return new UserDetailsImpl(usuario);
    }
}
