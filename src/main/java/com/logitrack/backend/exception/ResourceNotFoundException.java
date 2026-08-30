package com.logitrack.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // le ponemos la equeta a el error que sea un 404 y eso es lo que va a aparecer en postman
public class ResourceNotFoundException extends  RuntimeException{ // eredamos todos los tributos de runtimeException
    public ResourceNotFoundException(String mensaje){
        super(mensaje); // se comunica con el padre que es RuntimeException para que guarde el mesaje personalizado en la memoria
    }
}
