package com.logitrack.backend.model;

import com.logitrack.backend.listener.AuditoriaListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // genera los getters y los setters ademas del metodo toString(), es la ayuda de lombok
@NoArgsConstructor // genera el constructor basio
@AllArgsConstructor // genera el contructor lleno el que tiene todos los campos el mero mero
@Entity
@Table(name = "bodegas")
@EntityListeners(AuditoriaListener.class)
public class Bodega {

    @Id // le dice a la base de datos que este es el id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // le dice que es un valor Autoincrementable y lo llena la base de datos
    private Long id; // seguun google Long es un tipo de variable que soporta 9 trillones para que no nos quedemos sin ids

    @NotBlank(message = "El nombre de la bodega es un dato obligatorio")
    @Column(nullable = false,length = 100) // dato obligatorio, y limite de texto es 100
    private String nombre;

    @NotBlank(message = "La ubucacion es un dato obligatorio")
    @Column(nullable = false)
    private String ubicacion;

    @NotNull(message = "La capacidad es un dato obligatorio para una bodega")
    @Min(value = 0,message = "La capacidad no puede ser negativa")
    @Column(nullable = false)
    private Integer capacidad; //usamos integer en lugar del primitivo int pq en bases de datos aveces es necesario datos nulos cosa que int no resiste

    @NotBlank(message = "El encargado es un dato obligatorio")
    @Column(nullable = false,length = 100)
    private String encargado;

}
