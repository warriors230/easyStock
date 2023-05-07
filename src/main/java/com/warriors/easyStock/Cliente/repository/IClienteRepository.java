package com.warriors.easyStock.Cliente.repository;

import com.warriors.easyStock.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente,Integer> {
}