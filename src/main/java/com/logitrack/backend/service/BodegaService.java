package com.logitrack.backend.service;

import com.logitrack.backend.model.Bodega;
import com.logitrack.backend.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// el servicio es como el que se encarga que hacer que todo este en orden antes de pasarlo a la basde de datos

@Service // le decimos a spring que este archivo es un servicio y lo trate como tal
public class BodegaService {

    @Autowired // indica que se traiga todas las dependencias del archivo BodegaRepository para que pueda usarlas y comunicarme con la base de datos
    private BodegaRepository bodegaRepository;

    // 1. Resgistrar / crear un bodega
    public Bodega guardarBodega(Bodega bodega){
        return bodegaRepository.save(bodega); // tan facil como usar el BodegaRepository y escribir .save asi de facil guardamos una nueva bodega en spring boot
    }

    // 2. Consultar todas las bodegas
    public List<Bodega> obtenerTodas(){
        return bodegaRepository.findAll();
    }

    // 3. Consultar una bodega especifica por su ID
    public Optional<Bodega> obtenerPorId(Long id){ // el Optional<> es usado en caso de no encontrar ningun dato que coinsida para devulva un nulo
        return bodegaRepository.findById(id);
    }

    // 4. Eliminar una bodega
    public void eliminarBodega(Long id){
        bodegaRepository.deleteById(id);
    }

    // 5. PUT: Actializar una bodega existente
    public Optional<Bodega> actualizarBodega(Long id, Bodega bodegaDetalles){
        return bodegaRepository.findById(id).map(bodegaExistente -> { //el .map sirve para que el mismo revise si esta vacio o si tiene algo, si tiene algo devulve un objero bodegaExistente y si no el Optional vacio

            //Actializamos con los nuevos datos
            bodegaExistente.setNombre(bodegaDetalles.getNombre());
            bodegaExistente.setUbicacion(bodegaDetalles.getUbicacion());
            bodegaExistente.setCapacidad(bodegaDetalles.getCapacidad());
            bodegaExistente.setEncargado(bodegaDetalles.getEncargado());

            return bodegaRepository.save(bodegaExistente);
        }); //al finalizar le proceso esto retorna un objeto Optional con el .save si tiene algo y un empty si no hay nada
    }

}
