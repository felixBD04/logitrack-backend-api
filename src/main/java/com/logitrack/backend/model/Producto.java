package com.logitrack.backend.model;

import com.logitrack.backend.listener.AuditoriaListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
@EntityListeners(AuditoriaListener.class)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacio") // perfecto para validar que no allan datos vacios o con puro espacio
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "la categoria es obligatoria")
    @Column(nullable = false, length = 50)
    private String categoria;

    @NotNull(message = "El stok es obligatorio")
    @Min(value = 0, message = "El stok no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

}