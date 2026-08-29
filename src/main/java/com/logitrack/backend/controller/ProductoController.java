package com.logitrack.backend.controller;

import com.logitrack.backend.model.Producto;
import com.logitrack.backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 1. POST: crear un nuevo prodcuto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }

    // 2. GET: Obtener todos los productos
    @GetMapping
    public List<Producto> listarProdcutos(){
        return productoService.obtenerProductos();
    }

    // 3. GET: obtner producto por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        Optional<Producto> producto = productoService.obtenerPorId(id);
        if (producto.isPresent()){
            return ResponseEntity.ok(producto.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // 4. PUT: actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoExistente){
        Optional<Producto> productoActualizado = productoService.actualizarProducto(id,productoExistente);
        if (productoActualizado.isPresent()){
            return  ResponseEntity.ok(productoActualizado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE : eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
