-- ==========================================
-- INSERCIÓN DE USUARIOS
-- (Contraseña para todos: 'password123')
-- ==========================================

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (1, 'admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (2, 'empleado', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (3, 'juan_admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (4, 'carlos_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (5, 'ana_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (6, 'luis_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (7, 'sofia_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (8, 'marta_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (9, 'diego_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');

INSERT IGNORE INTO usuarios (id, username, password, rol)
VALUES (10, 'laura_emp', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'EMPLEADO');


-- ==========================================
-- INSERCIÓN DE BODEGAS
-- ==========================================

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (1, 'Bodega Central', 'Norte', 5000, 'Carlos Perez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (2, 'Bodega Sur', 'Sur', 3000, 'Ana Gomez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (3, 'Bodega Occidente', 'Occidente', 4500, 'Luis Martinez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (4, 'Bodega Oriente', 'Oriente', 2500, 'Marta Silva');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (5, 'Bodega Centro', 'Centro', 3800, 'Jorge Ramirez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (6, 'Bodega Américas', 'Suroccidente', 6000, 'Laura Torres');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (7, 'Bodega Suba', 'Noroccidente', 4000, 'Diego Castro');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (8, 'Bodega Fontibón', 'Occidente', 5500, 'Sofia Lopez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (9, 'Bodega Kennedy', 'Suroccidente', 3200, 'Pedro Sanchez');

INSERT IGNORE INTO bodegas (id, nombre, ubicacion, capacidad, encargado)
VALUES (10, 'Bodega Usaquén', 'Nororiente', 2800, 'Camila Rojas');


-- ==========================================
-- INSERCIÓN DE PRODUCTOS
-- ==========================================

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (1, 'Portatil XPS', 'Electronica', 50, 4500.00);

-- Stock bajo (<10)
INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (2, 'Monitor 4K', 'Electronica', 8, 1200.00);

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (3, 'Teclado Mecánico RGB', 'Perifericos', 25, 350.00);

-- Stock bajo (<10)
INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (4, 'Ratón Inalámbrico', 'Perifericos', 5, 120.00);

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (5, 'Auriculares con Cancelación de Ruido', 'Accesorios', 30, 450.00);

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (6, 'Disco Duro Externo 2TB', 'Almacenamiento', 40, 280.00);

-- Stock bajo (<10)
INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (7, 'Unidad SSD NVMe 1TB', 'Almacenamiento', 4, 350.00);

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (8, 'Memoria RAM 16GB DDR4', 'Componentes', 45, 200.00);

-- Stock bajo (<10)
INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (9, 'Tarjeta Gráfica RTX 3060', 'Componentes', 2, 1800.00);

INSERT IGNORE INTO productos (id, nombre, categoria, stock, precio)
VALUES (10, 'Hub USB-C 7 en 1', 'Accesorios', 60, 95.00);