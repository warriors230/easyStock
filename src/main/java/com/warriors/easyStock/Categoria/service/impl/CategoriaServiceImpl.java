package com.warriors.easyStock.Categoria.service.impl;

import com.warriors.easyStock.Categoria.entities.Categoria;
import com.warriors.easyStock.Categoria.repository.ICategoriaRepository;
import com.warriors.easyStock.Categoria.service.ICategoriaService;
import com.warriors.easyStock.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;


    @Override
    public Categoria crearCategoria(Categoria categoria) {
        categoria.setNombre(categoria.getNombre().toUpperCase());
        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categoriasBD = categoriaRepository.findAll();
        if (categoriasBD.isEmpty()) {
            throw new NotFoundException("No hay categorias en el sistema");
        }
        return categoriasBD;
    }


    @Override
    public Categoria editarCategoria(int id, Categoria categoria) {
        try {
            Categoria categoriaBD = buscarId(id);
            if (categoriaBD != null) {
                categoriaBD.setNombre(categoria.getNombre());
                return categoriaBD;
            }
        } catch (Exception e) {
        }
        throw new NotFoundException("No se encuentra la categoria con id: " + categoria.getIdCategoria());
    }

    @Override
    public void eliminarCategoria(int id) {
        try {
            Categoria categoriaBD = buscarId(id);
            if (categoriaBD != null) {
                categoriaRepository.deleteById(id);
            } else {
                throw new NotFoundException("No se encuentra la categoria con id: " + id);
            }

        } catch (Exception e) {
        }
    }

    @Override
    public Categoria buscarId(int id) {
        return categoriaRepository.findByIdCategoria(id);
    }


}
