-- Modelo de base de datos: cart_shopping_indra_db
-- Autor: Jonatan Echeverry

CREATE TABLE producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE carrito (
    id SERIAL PRIMARY KEY,
    total DECIMAL(10, 2) DEFAULT 0
);

CREATE TABLE carrito_producto (
    id SERIAL PRIMARY KEY,
    carrito_id INT REFERENCES carrito(id) ON DELETE CASCADE,
    producto_id INT REFERENCES producto(id) ON DELETE CASCADE,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL
);

CREATE TABLE cupon (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    descuento DECIMAL(5, 2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);