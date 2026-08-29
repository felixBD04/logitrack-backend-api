package com.logitrack.backend.controller;

import com.logitrack.backend.model.Movimiento;
import com.logitrack.backend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento){
        try {
            Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            return  ResponseEntity.ok(nuevoMovimiento);
        } catch (RuntimeException e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Movimiento> listarMovimientos(){
        return movimientoService.obtenerTodosLosMovimientos();
    }

}
