package com.warriors.easyStock.Usuario.controller;


import com.warriors.easyStock.Usuario.dto.UsuarioNuevoDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.utils.exceptions.ExistException;
import com.warriors.easyStock.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuarios(@RequestBody UsuarioNuevoDTO usuario) {
        if (usuarioService.existsByNombre(usuario.getNombre())) {
            throw new ExistException("Nombre " + usuario.getNombre());
        }
        if (usuarioService.existsByCorreo(usuario.getCorreo())) {
            throw new ExistException("correo " + usuario.getCorreo());
        }
        return ResponseEntity.ok().body(usuarioService.crearUsuario(usuario));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        if (usuarioService.ListarUsuarios() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usuarioService.ListarUsuarios());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioNuevoDTO usuario) {
        if (usuario != null) {
            return ResponseEntity.ok().body(usuarioService.editarUsuarios(id, usuario));
        } else {
            throw new NotFoundException("usuario " + usuario.getNombre());
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Usuario usuarioBD = usuarioService.eliminarUsuarios(id);
        if (usuarioBD != null) {
            return ResponseEntity.ok().body("Se elimino el usuario "+usuarioBD.getNombre());
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}