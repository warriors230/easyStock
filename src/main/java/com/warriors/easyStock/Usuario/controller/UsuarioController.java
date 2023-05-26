package com.warriors.easyStock.Usuario.controller;


import com.warriors.easyStock.Usuario.dto.UsuarioNuevoDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.Utils.exceptions.ExistException;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioNuevoDTO usuario) {
        return ResponseEntity.ok().body(usuarioService.editarUsuarios(id, usuario));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Usuario usuarioBD = usuarioService.eliminarUsuarios(id);
        return ResponseEntity.ok().body("Se elimino el usuario " + usuarioBD.getNombre());

    }
}