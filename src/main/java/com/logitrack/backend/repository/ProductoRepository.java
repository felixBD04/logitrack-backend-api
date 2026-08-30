package com.logitrack.backend.repository;

import com.logitrack.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // spring es tan maravillos que esto significa que se traiga los que tengan el stok menos a 10
    // SELECT * FROM productos WHERE stock < ?
    List<Producto> findByStockLessThan(Integer cantidad);
}