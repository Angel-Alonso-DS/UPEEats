CREATE DATABASE CafeteriaUniversitaria;
USE CafeteriaUniversitaria;

-- Usuarios
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    rol ENUM('estudiante', 'empleado', 'administrador') NOT NULL,
    matricula VARCHAR(20),
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Productos
CREATE TABLE Productos (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_producto VARCHAR(100) NOT NULL,
    imagenURL TEXT NOT NULL,
    descripcion TEXT  NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    tiempo_preparacion TIME NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Categorias
CREATE TABLE Categorias (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT NOT NULL
);

-- Relacion muchos a muchos entre productos y categorias
CREATE TABLE Productos_Categorias (
    id_producto INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_producto, id_categoria),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

-- Pedidos
CREATE TABLE Pedidos (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    tiempo_estimado TIME NOT NULL,
    tiempo_entrega TIME NOT NULL,
    estado ENUM('pendiente', 'preparando', 'listo', 'entregado', 'cancelado') NOT NULL DEFAULT 'pendiente',
    comentario TEXT,
    total DECIMAL(10,2) NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);


-- Detalle de pedidos

CREATE TABLE DetallePedido (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(8,2) NOT NULL,
    observaciones TEXT,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Resenia para pedidos
CREATE TABLE ReseniaPedido (
    id_resenia INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_pedido INT NOT NULL,
    calificacion ENUM('1', '2', '3', '4', '5') NOT NULL,
    comentario TEXT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido)
);

-- Resenia para productos
CREATE TABLE ReseniaProductos (
    id_resenia INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    calificacion ENUM('1', '2', '3', '4', '5') NOT NULL,
    comentario TEXT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Sugerencia alimenticias
CREATE TABLE Sugerencias (
    id_sugerencia INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    tipo_dieta VARCHAR(100),
    alergias TEXT,
    comentarios TEXT NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Notificaciones
CREATE TABLE Notificaciones (
    id_notificacion INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_pedido INT NOT NULL,
    mensaje TEXT NOT NULL,
    fecha DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido)
);
