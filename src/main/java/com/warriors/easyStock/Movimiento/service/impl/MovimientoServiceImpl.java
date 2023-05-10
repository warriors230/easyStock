package com.warriors.easyStock.Movimiento.service.impl;

import com.warriors.easyStock.Movimiento.entities.ItemMovimiento;
import com.warriors.easyStock.Movimiento.entities.Movimiento;
import com.warriors.easyStock.Movimiento.repository.IMovimientoRepository;
import com.warriors.easyStock.Movimiento.service.IItemMovimientoService;
import com.warriors.easyStock.Movimiento.service.IMovimientoService;
import com.warriors.easyStock.Producto.entities.Producto;
import com.warriors.easyStock.Producto.service.IProductoService;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.utils.ConstantesSistema;
import com.warriors.easyStock.utils.exceptions.ConflictException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class MovimientoServiceImpl implements IMovimientoService {


    IUsuarioService usuarioService;

    IMovimientoRepository movimientoRepository;

    IItemMovimientoService itemMovimientoService;

    IProductoService productoService;


    @Override
    public Movimiento guardarMovimiento(Movimiento movimiento, int codigoCliente, int codigoVendedor) {
        Usuario cliente = usuarioService.buscarId(codigoCliente);
        Usuario vendedor = usuarioService.buscarId(codigoVendedor);
        ItemMovimiento itemMovimientos = new ItemMovimiento();

        Producto producto;
        movimiento.setCliente(cliente);
        movimiento.setVendedor(vendedor);

        if (movimiento.getTipoMovimiento().equals(ConstantesSistema.MOVIMIENTO_COMPRA)) {
            for (ItemMovimiento item : movimiento.getItemMovimientos()) {
                producto = productoService.buscarSerial(item.getProducto().getSerialID());
                if (producto != null) {
                    producto.setCantidadStock(producto.getCantidadStock() + item.getCantidad());
                    productoService.editarProducto(producto.getId(),producto);
                    itemMovimientoService.crearItem(item);

                }else{
                    producto.setCantidadStock(item.getCantidad());
                    producto.setEstado(true);
                    productoService.crearProducto(producto);
                    itemMovimientoService.crearItem(item);
                }
             }
            movimiento.setValorMovimiento(movimiento.getTotal()-movimiento.calcularDescuento());
            movimientoRepository.save(movimiento);
        } else {
            for (ItemMovimiento item : movimiento.getItemMovimientos()) {
                producto = productoService.buscarId(item.getProducto().getId());
                if (producto.getCantidadStock() == 0) {
                    throw new ConflictException("No tiene cantidad disponible en el inventario para realizar la venta");
                }
                itemMovimientos.setCantidad(item.getCantidad());
                itemMovimientos.setProducto(item.getProducto());
                producto.setCantidadStock(producto.getCantidadStock() - item.getCantidad());
                productoService.editarProducto(producto.getId(), producto);
                itemMovimientoService.crearItem(itemMovimientos);

            }
        }


        // Lineas del movimiento
        // Guardamos los item


        return null;
    }
}
