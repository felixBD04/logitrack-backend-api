package com.logitrack.backend.controller;

import com.logitrack.backend.model.Bodega;
import com.logitrack.backend.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //indica que esta clase es un punto de entrada para intenert, comunicarse con el exterior todo lo que traiga debe ser json el idioma unviersal de las apis
@RequestMapping("/api/bodegas") //definimos la ruta para poder acceder a la api en este caso seria algo asi "http://localhost:8080/api/bodegas"
public class BodegaController {

    @Autowired
    private BodegaService bodegaService; // traemos todas las dependecians de BodegaService

    // 1. POST: Crear una nueva bodega
    @PostMapping //es uno de los metods HTTP como estamos creado datos en necesaio el metodo POST
    public Bodega crearBodega(@RequestBody Bodega bodega){ //RequestBody obtine el json que manda el cliente y lo convierte en un objeto bodega para poder guardarlo en la base de datos
        return bodegaService.guardarBodega(bodega);
    }

    // 2. GET : Obtener todas las bodegas
    @GetMapping// metodo HTTP para poder sacar informacion de la base de datos
    public List<Bodega> listarBodegas(){
        return bodegaService.obtenerTodas();
    }

    // 3. GET : Obtener una bodega por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerBodega(@PathVariable Long id){ //PathVariable extrae la varible que biene en la ruta en este caso el id api/bodegas/5
        Bodega bodega = bodegaService.obtenerPorId(id);
        return ResponseEntity.ok(bodega); // con el ResponseEntity podemos manipular el codigo HTTP para mandar el mesaje seguin correponda en este caso codigo 200 de que si se contro
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBodega(@PathVariable Long id){
        bodegaService.eliminarBodega(id);
        return ResponseEntity.noContent().build(); //devuelve 204 de no content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> actualizarBodega(@PathVariable Long id, @RequestBody Bodega bodegaDetalles){
        Bodega bodegaActualizada = bodegaService.actualizarBodega(id, bodegaDetalles);
        return ResponseEntity.ok(bodegaActualizada);
    }

}
