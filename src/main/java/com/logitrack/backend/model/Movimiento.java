package com.logitrack.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // lo que hace es decirle a a la base de datos que guarde el Enum como texto y no con un numero para leerlo mas facil
    @Column(nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Min(value = 1)
    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String usuarioResponsable; // Por ahora será un texto, luego lo conectaremos con el login

    // Relaciones (Llaves Foraneas)
    @ManyToOne //le indicamos a la base de datos la relacion que va a haber entre las tablas
    @JoinColumn(name = "producto_id", nullable = false) // y tambien el nombre de la columna
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "bodega_origen_id")
    private Bodega bodegaOrigen; // la informacion de la bodega de la que sale

    @ManyToOne
    @JoinColumn(name = "bodega_destino_id")
    private Bodega bodegaDestino; // la informacion de la bodega a la que va
}