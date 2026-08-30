package com.logitrack.backend.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// este es el puente que conecta a los EntityListeners con Spring Boot ya que estan en universos diferentes
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext context; // esta variable estatica es la que contiene todos los repositorio y de donde los vamos a sacar

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext; // aca es donde le ponemos todos los respositorios
    }

    // Este es el método mágico que nos permitirá sacar el Repositorio desde cualquier lado en este caso Hibernate que es donde incialemte no lo podemos tener y por lo que hacemos el puente
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}