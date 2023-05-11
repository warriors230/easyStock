package com.warriors.easyStock.Movimiento.service.impl;

import com.warriors.easyStock.Movimiento.entities.ItemMovimiento;
import com.warriors.easyStock.Movimiento.repository.IItemMovimientoRepository;
import com.warriors.easyStock.Movimiento.service.IItemMovimientoService;
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
