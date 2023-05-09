package com.warriors.easyStock.Producto.controller;

import com.warriors.easyStock.Producto.entities.Producto;
import com.warriors.easyStock.Producto.service.IProductoService;
import com.warriors.easyStock.Usuario.dto.UsuarioNuevoDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.utils.exceptions.ExistException;
import com.warriors.easyStock.utils.exceptions.NotFoundException;
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

    @PostMapping("/crear")
    public ResponseEntity<?> crearProductos(@RequestBody Producto producto) {
        return ResponseEntity.ok().body(productoService.crearProducto(producto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VENDEDOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok().body(productoService.listarProductos());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Producto producto) {
        return ResponseEntity.ok().body(productoService.editarProducto(id, producto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Producto productoBD = productoService.eliminarProducto(id);
        return ResponseEntity.ok().body("Se elimino el producto " + productoBD.getDescripcion());

    }
}
