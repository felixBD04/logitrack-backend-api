package com.logitrack.backend.repository;

import com.logitrack.backend.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    // spring boot es super intutito con las palabras claves en el nombre se logran esas busquedas
    // SELECT * FROM movimientos WHERE fecha BETWEEN ? AND ?
    List<Movimiento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
