package com.warriors.easyStock.Producto.service;

import com.warriors.easyStock.Producto.dto.ProductoDTO;
import com.warriors.easyStock.Producto.entities.Producto;


import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public Producto crearProducto(Producto producto);

    public Producto editarProducto(int id, Producto producto);
    public Producto eliminarProducto(int id);

    public Producto buscarId(int id);
    public Producto buscarSerial(String serial);
}
