package com.logitrack.backend.service;

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
    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }

    // 4. modificar producto
    public Optional<Producto> actualizarProducto(Long id, Producto productoModificado){
        return productoRepository.findById(id).map( productoExistente -> {

            productoExistente.setCategoria(productoModificado.getCategoria());
            productoExistente.setNombre(productoModificado.getNombre());
            productoExistente.setPrecio(productoModificado.getPrecio());
            productoExistente.setStock(productoModificado.getStock());

            return productoRepository.save(productoExistente);
        });
    }

    // 5. Eliminar Producto
    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
