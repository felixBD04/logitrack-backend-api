-- Este script define la estructura de las tablas para LogiTrack

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS bodegas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    encargado VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    precio DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimiento VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL,
    cantidad INT NOT NULL,
    usuario_responsable VARCHAR(255) NOT NULL,
    producto_id BIGINT NOT NULL,
    bodega_origen_id BIGINT,
    bodega_destino_id BIGINT,
    FOREIGN KEY (producto_id) REFERENCES productos(id),
    FOREIGN KEY (bodega_origen_id) REFERENCES bodegas(id),
    FOREIGN KEY (bodega_destino_id) REFERENCES bodegas(id));

CREATE TABLE IF NOT EXISTS auditorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operacion VARCHAR(50) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    entidad_afectada VARCHAR(255) NOT NULL,
    valores_anteriores TEXT,
    valores_nuevos TEXT);