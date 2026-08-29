package com.logitrack.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operacion; // Guardará "INSERT", "UPDATE" o "DELETE"

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String usuario; // Quién hizo el cambio

    @Column(nullable = false)
    private String entidadAfectada; // Ej: "Bodega" o "Producto"

    // Usamos TEXT porque un JSON con los valores puede ser muy largo para un VARCHAR normal
    @Column(columnDefinition = "TEXT")
    private String valoresAnteriores;

    @Column(columnDefinition = "TEXT")
    private String valoresNuevos;
}