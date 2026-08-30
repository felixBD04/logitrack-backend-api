package com.logitrack.backend.service;

import com.logitrack.backend.exception.ResourceNotFoundException;
import com.logitrack.backend.model.Producto;
import com.logitrack.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Crear un nuevo producto
    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    // 2. listar los productos
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    // 3. buscar producto por id
    public Producto obtenerPorId(Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
    }

    // 4. modificar producto
    public Producto actualizarProducto(Long id, Producto productoModificado){
        // Primero verificamos si existe (si no, salta la alarma)
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Producto no encontrado con el ID: " + id));

        // Si llegó hasta aquí, es porque existe. Lo actualizamos:
        productoExistente.setCategoria(productoModificado.getCategoria());
        productoExistente.setNombre(productoModificado.getNombre());
        productoExistente.setPrecio(productoModificado.getPrecio());
        productoExistente.setStock(productoModificado.getStock());

        return productoRepository.save(productoExistente);
    }

    // 5. Eliminar Producto
    public void eliminarProducto(Long id){
        // Verificamos si existe antes de intentar borrar algo fantasma
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Producto no encontrado con el ID: " + id));

        productoRepository.delete(productoExistente);
    }

    // 6. informe de stok bajo menor a 10
    public  List<Producto> productosConStokBajo(Integer cantidad){
        return productoRepository.findByStockLessThan(cantidad);
    }
}
