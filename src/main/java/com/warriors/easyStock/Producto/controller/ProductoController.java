package com.warriors.easyStock.Producto.controller;

import com.warriors.easyStock.Producto.entities.Producto;
import com.warriors.easyStock.Producto.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private IProductoService productoService;

    @Autowired
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPREMO','ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> crearProductos(@RequestBody Producto producto) {
        return ResponseEntity.ok().body(productoService.crearProducto(producto));
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPREMO','ROLE_ADMIN','ROLE_VENDEDOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok().body(productoService.listarProductos());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Producto producto) {
        return ResponseEntity.ok().body(productoService.editarProducto(id, producto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPREMO')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Producto productoBD = productoService.eliminarProducto(id);
        return ResponseEntity.ok().body("Se elimino el producto " + productoBD.getDescripcion());

    }
}
