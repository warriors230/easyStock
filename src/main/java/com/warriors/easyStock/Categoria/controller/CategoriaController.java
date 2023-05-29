package com.warriors.easyStock.Categoria.controller;

import com.warriors.easyStock.Categoria.entities.Categoria;
import com.warriors.easyStock.Categoria.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @PostMapping("/crear")
    public ResponseEntity<Categoria> crearCategria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.crearCategoria(categoria));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoria, @PathVariable Integer idCategoria) {
        return ResponseEntity.ok(categoriaService.editarCategoria(idCategoria, categoria));
    }

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok().build();
    }

}

