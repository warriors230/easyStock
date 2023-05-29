package com.warriors.easyStock.Categoria.repository;

import com.warriors.easyStock.Categoria.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria,Integer> {
    public Categoria findByIdCategoria(int id);
}
