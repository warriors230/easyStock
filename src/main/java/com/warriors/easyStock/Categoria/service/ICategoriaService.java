package com.warriors.easyStock.Categoria.service;

import com.warriors.easyStock.Categoria.entities.Categoria;

import java.util.List;
import java.util.Optional;


public interface ICategoriaService {
    public Categoria crearCategoria(Categoria categoria);
    public List<Categoria> listarCategorias();
    public Categoria editarCategoria(int id, Categoria categoria);
    public void eliminarCategoria(int id);
   public Categoria   buscarId(int id);
}
