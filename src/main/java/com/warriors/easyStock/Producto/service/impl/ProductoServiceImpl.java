package com.warriors.easyStock.Producto.service.impl;


import com.warriors.easyStock.Producto.entities.Producto;
import com.warriors.easyStock.Producto.repository.IProductoRepository;
import com.warriors.easyStock.Producto.service.IProductoService;
import com.warriors.easyStock.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ProductoServiceImpl implements IProductoService {


    private IProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        List<Producto> listaProductos = productoRepository.findAll();
        if (listaProductos.isEmpty()) {
            throw new NotFoundException("No hay productos en el sistema!");
        }
        return listaProductos;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        producto.setGanancia(producto.calcularGanancia());
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(int id, Producto producto) {
        Producto productoDB = productoRepository.findById(id).orElse(null);
        if (productoDB != null) {
            productoDB.setDescripcion(producto.getDescripcion());
            productoDB.setCantidadStock(producto.getCantidadStock());
            productoDB.setGanancia(producto.calcularGanancia());
            productoDB.setUnidadMedida(producto.getUnidadMedida());
            productoDB.setValorCompra(producto.getValorCompra());
            productoRepository.save(productoDB);

            return productoDB;
        }
        throw new NotFoundException("El producto con id " + id + " " + "No existe");
    }

    @Override
    public Producto eliminarProducto(int id) {
        Producto productoBD = productoRepository.findById(id).orElse(null);
        if (productoBD != null) {
            productoRepository.deleteById(id);
            return productoBD;
        } else {
            throw new NotFoundException("El producto con id " + id + " " + "No existe");
        }
    }

    @Override
    public Producto buscarId(int id) {
        Producto productoBD = productoRepository.findById(id).orElse(null);
        if (productoBD != null) {
            return productoBD;
        } else {
            throw new NotFoundException("El producto con id " + id + " " + "No existe");
        }

    }

    @Override
    public Producto buscarSerial(String serial) {
        return productoRepository.findBySerialID(serial).orElse(null);
    }
}
