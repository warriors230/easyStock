package com.warriors.easyStock.Movimiento.service.impl;

import com.warriors.easyStock.Movimiento.dto.MovimientoRespnseDTO;
import com.warriors.easyStock.Movimiento.entities.ItemMovimiento;
import com.warriors.easyStock.Movimiento.entities.Movimiento;
import com.warriors.easyStock.Movimiento.repository.IMovimientoRepository;
import com.warriors.easyStock.Movimiento.service.IItemMovimientoService;
import com.warriors.easyStock.Movimiento.service.IMovimientoService;
import com.warriors.easyStock.Producto.entities.Producto;
import com.warriors.easyStock.Producto.service.IProductoService;
import com.warriors.easyStock.Usuario.dto.UsuarioResponseDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Usuario.service.IUsuarioService;
import com.warriors.easyStock.utils.constants.ConstantesSistema;
import com.warriors.easyStock.utils.exceptions.ConflictException;
import com.warriors.easyStock.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class MovimientoServiceImpl implements IMovimientoService {


    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IMovimientoRepository movimientoRepository;
    @Autowired
    IItemMovimientoService itemMovimientoService;
    @Autowired
    IProductoService productoService;


    @Override
    @Transactional(rollbackFor = {ConflictException.class, NullPointerException.class})
    public MovimientoRespnseDTO guardarMovimiento(Movimiento movimiento, int codigoVendedor, int codigoCliente) {
        Usuario vendedor = usuarioService.buscarId(codigoVendedor);

        Usuario cliente = usuarioService.buscarId(codigoCliente);

        UsuarioResponseDTO vendedordto = UsuarioResponseDTO
                .builder()
                .nombre(vendedor.getNombre())
                .documento(vendedor.getDocumento())
                .telefono(vendedor.getTelefono())
                .direccion(vendedor.getDireccion())
                .ciudad(vendedor.getCiudad())
                .estado(vendedor.getEstado())
                .build();

        UsuarioResponseDTO clientedto = UsuarioResponseDTO
                .builder()
                .nombre(cliente.getNombre())
                .documento(cliente.getDocumento())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .ciudad(cliente.getCiudad())
                .estado(cliente.getEstado())
                .build();


        List<ItemMovimiento> lsitemMovimientos = new ArrayList<>();

        Producto producto;
        movimiento.setCliente(cliente);
        movimiento.setVendedor(vendedor);

        if (movimiento.getTipoMovimiento().equals(ConstantesSistema.MOVIMIENTO_COMPRA)) {
            for (ItemMovimiento item : movimiento.getItemMovimientos()) {
                producto = productoService.buscarSerial(item.getProducto().getSerialID());
                if (producto != null) {
                    item.setProductoCantidadAnterior(item.getProducto().getCantidadStock());
                    item.setProductoCantidadActual(item.getProducto().getCantidadStock() + item.getCantidad());
                    producto.setCantidadStock(producto.getCantidadStock() + item.getCantidad());
                    productoService.editarProducto(producto.getId(), producto);
                } else {
                    producto = item.getProducto();
                    producto.setCantidadStock(item.getCantidad());
                    producto.setEstado(false);
                    productoService.crearProducto(producto);
                }
                item.setValorItemMovimiento(item.calcularImporteCompra());
                itemMovimientoService.crearItem(item);
                lsitemMovimientos.add(item);
            }
            movimiento.setItemMovimientos(lsitemMovimientos);
            movimiento.setValorMovimiento(movimiento.getTotalCompra());
            movimiento.setValorMovimiento(movimiento.getValorMovimiento() - movimiento.calcularDescuento());

        } else {
            for (ItemMovimiento item : movimiento.getItemMovimientos()) {
                producto = productoService.buscarId(item.getProducto().getId());
                if (producto.getCantidadStock() == 0) {
                    throw new ConflictException("No tiene cantidad disponible en el inventario para realizar la venta");
                } else if (producto.getCantidadStock() - item.getCantidad() < 0) {
                    throw new ConflictException("La cantidad solicitada sobrepasa la cantidad en el inventario. Posee " + producto.getCantidadStock()
                            + " cantidades de " + producto.getDescripcion() + " en el inventario");
                }
                item.setProductoCantidadAnterior(item.getProducto().getCantidadStock());
                item.setProductoCantidadActual(producto.getCantidadStock() - item.getCantidad());
                item.setValorItemMovimiento(item.calcularImporteVenta());
                producto.setCantidadStock(producto.getCantidadStock() - item.getCantidad());
                productoService.editarProducto(producto.getId(), producto);
                itemMovimientoService.crearItem(item);
                lsitemMovimientos.add(item);
            }
            movimiento.setItemMovimientos(lsitemMovimientos);
            movimiento.setValorMovimiento(movimiento.getTotalVenta());
            movimiento.setValorMovimiento(movimiento.getValorMovimiento() - movimiento.calcularDescuento());
        }
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);

        return MovimientoRespnseDTO.builder()
                .id(movimientoGuardado.getId())
                .descripcion(movimientoGuardado.getDescripcion())
                .tipoMovimiento(movimientoGuardado.getTipoMovimiento())
                .valorMovimiento(movimientoGuardado.getValorMovimiento())
                .descuentoAplicado(movimientoGuardado.getDescuentoAplicado())
                .vendedor(vendedordto)
                .cliente(clientedto)
                .itemMovimientos(movimientoGuardado.getItemMovimientos())
                .fechaMovimiento(movimientoGuardado.getFechaMovimiento())
                .idRemitente(movimientoGuardado.getIdRemitente())
                .idDestino(movimientoGuardado.getIdDestino())
                .estado(movimiento.getEstado())
                .cambio(movimiento.getCambio())
                .pendiente(movimiento.getPendiente())
                .build();

    }

    @Override
    public List<MovimientoRespnseDTO> listarMovimientos() {
        List<Movimiento> lsMovimientos = movimientoRepository.findAll();
        List<MovimientoRespnseDTO> lsMovimientoRespnseDTOS =
                lsMovimientos.stream().map(e -> {
                    MovimientoRespnseDTO movimientoRespnseDTO = MovimientoRespnseDTO.builder().
                            id(e.getId())
                            .descripcion(e.getDescripcion())
                            .valorMovimiento(e.getValorMovimiento())
                            .fechaMovimiento(e.getFechaMovimiento())
                            .tipoMovimiento(e.getTipoMovimiento())
                            .itemMovimientos(e.getItemMovimientos())
                            .vendedor(UsuarioResponseDTO.builder().nombre(e.getVendedor().getNombre())
                                    .documento(e.getVendedor().getDocumento())
                                    .telefono(e.getVendedor().getTelefono())
                                    .direccion(e.getVendedor().getDireccion())
                                    .ciudad(e.getVendedor().getCiudad())
                                    .estado(e.getVendedor().getEstado())
                                    .build())
                            .cliente(UsuarioResponseDTO.builder().nombre(e.getCliente().getNombre())
                                    .documento(e.getCliente().getDocumento())
                                    .telefono(e.getCliente().getTelefono())
                                    .direccion(e.getCliente().getDireccion())
                                    .ciudad(e.getCliente().getCiudad())
                                    .estado(e.getCliente().getEstado())
                                    .build())
                            .idRemitente(e.getIdRemitente())
                            .idDestino(e.getIdDestino())
                            .descuentoAplicado(e.getDescuentoAplicado())
                            .build();
                    return movimientoRespnseDTO;
                }).collect(Collectors.toList());

        if (lsMovimientoRespnseDTOS.isEmpty()) {
            throw new NotFoundException("No hay movimientos registrados en el sistema");
        }
        return lsMovimientoRespnseDTOS;
    }
}
