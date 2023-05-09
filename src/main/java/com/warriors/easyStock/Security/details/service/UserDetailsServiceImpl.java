package com.warriors.easyStock.Security.details.service;

import com.warriors.easyStock.Security.details.UserDetailsImpl;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String userOrCorreo) throws UsernameNotFoundException {
        System.out.println(">>>>>>>>>>>>>>>>>>>"+userOrCorreo);
        Usuario usuario = usuarioRepository.findByUsuarioOrCorreo(userOrCorreo,userOrCorreo).get();
        return UserDetailsImpl.build(usuario);
    }
}
