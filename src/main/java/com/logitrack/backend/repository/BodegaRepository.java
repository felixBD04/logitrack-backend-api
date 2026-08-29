package com.logitrack.backend.repository;

import com.logitrack.backend.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// REPOSITORIO
// es como un tradcuto que habla con la base de datos nosotros le escribimos comosas como delete() que el traduce en una consula sql
// sirve de puente entre la base de datos y nosotros


@Repository // le dicemos a spring que este archivo es el encargado de hablar con la base de datos
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}

// lo que esta despues de extends sirve para decirle que herede todo el poder del JPA  de bodega y que tenga que el id es de tipo Long

// ya despues de esto tenemos todo listo pq spring data JPA hace todo el trabajo pesado para la comunicacion con la base de datos
