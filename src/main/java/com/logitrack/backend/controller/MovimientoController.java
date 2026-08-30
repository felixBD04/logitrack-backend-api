package com.logitrack.backend.controller;

import com.logitrack.backend.model.Movimiento;
import com.logitrack.backend.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@Valid @RequestBody Movimiento movimiento){
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
        return  ResponseEntity.ok(nuevoMovimiento);
    }

    @GetMapping
    public List<Movimiento> listarMovimientos(){
        return movimientoService.obtenerTodosLosMovimientos();
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Movimiento>> listarMovimeintosEnRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin){
        List<Movimiento> movimientos = movimientoService.movimientoSegunRangoFecha(inicio,fin);
        return ResponseEntity.ok(movimientos);
    }

}
