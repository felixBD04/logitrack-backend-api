package com.logitrack.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // convierte a esta clase en un vigilate global que esta pendiente de cualquier error
public class GlobalExceptionHandler {

    // esta pendiente de errores 404 para lazar el error
    @ExceptionHandler(ResourceNotFoundException.class) // si hay un error el se dara cuenta para ejecutal el codigo debajo
    public ResponseEntity<ErrorDetails> manejarResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) { // WebRequest es un objero de srping boot no va a decir toda la infromacion de la request que salio mal
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(), // fecha de error
                ex.getMessage(), // el mensaje de error capturado por nuestra clase
                webRequest.getDescription(false) // la ruta de la reques que causo el error
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // si los datos estan bien pero no es posible realizar la accion pq por ejemplo no hay suficiente stock el error entra aca
    @ExceptionHandler(BadRequestException.class) // pendiente para devolver el error 400 de que el cliente se equivoco
    public ResponseEntity<ErrorDetails> manejarBadRequestException(BadRequestException ex, WebRequest webRequest) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // atrapa errores generales inesperados como el 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> manejarGlobalException(Exception ex, WebRequest webRequest) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                "Ocurrió un error interno en el servidor: " + ex.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // es el encargado de camputar todos lo errores que se den por la mala dijitacion el usuario
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>(); // lista con los errores por los que no sirve

        // Extraemos todos los errores y armamos un JSON de tipo { "campo": "mensaje" }
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        }); // obtenemos todos los errores y los almacenamos en la varible antes mencionada

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}