package com.warriors.easyStock.Usuario.controller;


import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuarios(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.crearUsuario(usuario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        if (usuarioService.ListarUsuarios() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usuarioService.ListarUsuarios());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario usuario) {
        if (usuario != null) {
            return ResponseEntity.ok().body(usuarioService.editarUsuarios(id, usuario));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        Usuario usuarioBD = usuarioService.eliminarUsuarios(id);
        if (usuarioBD != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}