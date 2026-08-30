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

    // 3. GET: obtener producto por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    // 4. PUT: actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoExistente){
        Producto productoActualizado = productoService.actualizarProducto(id, productoExistente);
        return ResponseEntity.ok(productoActualizado);
    }

    // 5. DELETE : eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // 6. GET : Reposte de stok bajo
    @GetMapping("/bajo-stock")
    public ResponseEntity<List<Producto>> ListarProductosConBajoStock(@RequestParam(defaultValue = "10") Integer cantidad){

        List<Producto> productos = productoService.productosConStokBajo(cantidad);
        return ResponseEntity.ok(productos);
    }
}
