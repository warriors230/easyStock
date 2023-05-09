package com.warriors.easyStock.Security.controller;

import com.warriors.easyStock.Security.dto.JwtDto;
import com.warriors.easyStock.Security.dto.LoginUsuarioDTO;
import com.warriors.easyStock.Security.jwt.JwtProvider;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.utils.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuarioDTO loginUsuario) {
        System.out.println(usuarioService.existsByUsuario(loginUsuario.getCorreo()));

        if (!(usuarioService.existsByCorreo(loginUsuario.getCorreo()) || usuarioService.existsByUsuario(loginUsuario.getCorreo()))) {
            throw new BadRequestException("No existe el usuario en el sistema. Valide la información");
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
