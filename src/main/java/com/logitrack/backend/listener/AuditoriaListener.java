package com.logitrack.backend.listener;

import com.logitrack.backend.config.BeanUtil;
import com.logitrack.backend.model.Auditoria;
import com.logitrack.backend.repository.AuditoriaRepository;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AuditoriaListener {

    @PostPersist //el que esta pendiente justo despues de insertar un dato
    public void despuesDeInsertar(Object entidad) {
        //como es un dato nuevo no hay valores antiguos
        registrarAuditoria("INSERT", null, entidad.toString(), entidad);
    }

    @PostUpdate //el que esta pendiente justo despues de actualizar un dato
    public void despuesDeActualizar(Object entidad) {
        // para poder obtener esos valores antiguos de la entidad tendriamos que descargar una libreria que se encarga de eso por el momento no los tenemos
        registrarAuditoria("UPDATE", "No disponible en JPA básico", entidad.toString(), entidad);
    }

    @PreRemove // el que esta pendiente para poder leer los datos antes de que se borren
    public void antesDeEliminar(Object entidad) {
        // no hay valores nuevo pq la entidad ya no existe
        registrarAuditoria("DELETE", entidad.toString(), null, entidad);
    }

    private void registrarAuditoria(String operacion, String valoresAnteriores, String valoresNuevos, Object entidad) {

        AuditoriaRepository auditoriaRepository = BeanUtil.getBean(AuditoriaRepository.class);

        Auditoria auditoria = new Auditoria();
        auditoria.setOperacion(operacion);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setUsuario("SISTEMA");
        auditoria.setEntidadAfectada(entidad.getClass().getSimpleName());

        // ¡Aquí es donde mapeamos ambos valores!
        auditoria.setValoresAnteriores(valoresAnteriores);
        auditoria.setValoresNuevos(valoresNuevos);

        auditoriaRepository.save(auditoria);
    }
}