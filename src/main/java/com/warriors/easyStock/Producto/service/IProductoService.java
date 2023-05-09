package com.warriors.easyStock.Producto.service;

import com.warriors.easyStock.Producto.dto.ProductoDTO;
import com.warriors.easyStock.Producto.entities.Producto;


import java.util.List;

public interface IProductoService {
    public List<Producto> ListarProductos();
    public Producto crearProducto(Producto producto);

    public Producto editarProducto(int id, Producto producto);
    public Producto eliminarProducto(int id);
}
