package com.logitrack.backend.service;

import com.logitrack.backend.exception.BadRequestException;
import com.logitrack.backend.exception.ResourceNotFoundException;
import com.logitrack.backend.model.Movimiento;
import com.logitrack.backend.model.Producto;
import com.logitrack.backend.model.TipoMovimiento;
import com.logitrack.backend.repository.MovimientoRepository;
import com.logitrack.backend.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // consular todos los movimientos
    public List<Movimiento> obtenerTodosLosMovimientos(){
        return movimientoRepository.findAll();
    }

    public List<Movimiento> movimientoSegunRangoFecha(LocalDateTime inicio, LocalDateTime fin){
        return movimientoRepository.findByFechaBetween(inicio, fin);
    }

    @Transactional // esto basicamente es para el proceso de restar stock y guardar moviemnto se hagan los dos con exito o cancele la operacion
    public Movimiento registrarMovimiento(Movimiento movimiento){

        // colocamos la fecha actual del movimiento
        movimiento.setFecha(LocalDateTime.now());

        //obtenmos el producto desde la base de datos
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Error: El producto con el ID : "+movimiento.getProducto().getId()+" no existe en la base de datos")); // metodo del Optional para saber si esta vacio y arrojar un error

        //
        if (movimiento.getTipoMovimiento() == TipoMovimiento.ENTRADA){
            producto.setStock(producto.getStock() + movimiento.getCantidad()); //sumamos el stock al producto
        } else if (movimiento.getTipoMovimiento() == TipoMovimiento.SALIDA){
            if (movimiento.getCantidad() > producto.getStock()){ // verificamos que alla suficiente stok antes de el movimiento
                throw new BadRequestException("Error, no hay stock suficiente solo hay" + producto.getStock());
            }
            producto.setStock((producto.getStock() - movimiento.getCantidad())); //restamos la cantidad requerida
        }

        productoRepository.save(producto);
        return movimientoRepository.save(movimiento);
    }
}
