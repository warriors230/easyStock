package com.warriors.easyStock.movimiento.service.impl;

import com.warriors.easyStock.movimiento.entities.ItemMovimiento;
import com.warriors.easyStock.movimiento.repository.IItemMovimientoRepository;
import com.warriors.easyStock.movimiento.service.IItemMovimientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ItemMovimientoServiceImpl implements IItemMovimientoService {

    @Autowired
    IItemMovimientoRepository itemMovimientoRepository;

    @Override
    public ItemMovimiento crearItem(ItemMovimiento itemMovimiento) {
        return itemMovimientoRepository.save(itemMovimiento);
    }
}
