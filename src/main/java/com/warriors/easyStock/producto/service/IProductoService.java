package com.warriors.easyStock.producto.service;

import com.warriors.easyStock.producto.entities.Producto;


import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public Producto crearProducto(Producto producto);

    public Producto editarProducto(int id, Producto producto);
    public Producto eliminarProducto(int id);

    public Producto buscarId(int id);
    public Producto buscarSerial(String serial);
}
