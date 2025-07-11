
-- Usuarios
CREATE TABLE Usuarios (
    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido_paterno TEXT NOT NULL,
    apellido_materno TEXT NOT NULL,
    correo TEXT UNIQUE NOT NULL,
    contrasenia TEXT NOT NULL,
    telefono TEXT NOT NULL,
    activo INTEGER NOT NULL DEFAULT 1,
    rol TEXT NOT NULL,
    matricula TEXT,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Productos
CREATE TABLE Productos (
    id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_producto TEXT NOT NULL,
    imagenURL TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    precio REAL NOT NULL,
    tiempo_preparacion TEXT NOT NULL,
    disponible INTEGER NOT NULL DEFAULT 1,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Categorias
CREATE TABLE Categorias (
    id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT NOT NULL
);

-- Relacion muchos a muchos entre productos y categorias
CREATE TABLE Productos_Categorias (
    id_producto INTEGER NOT NULL,
    id_categoria INTEGER NOT NULL,
    PRIMARY KEY (id_producto, id_categoria),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

-- Pedidos
CREATE TABLE Pedidos (
    id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    tiempo_estimado TEXT NOT NULL,
    tiempo_entrega TEXT NOT NULL,
    estado TEXT NOT NULL DEFAULT 'pendiente',
    comentario TEXT,
    total REAL NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Detalle de pedidos
CREATE TABLE DetallePedido (
    id_detalle INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pedido INTEGER NOT NULL,
    id_producto INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    subtotal REAL NOT NULL,
    observaciones TEXT,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Resenia para pedidos
CREATE TABLE ReseniaPedido (
    id_resenia INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    id_pedido INTEGER NOT NULL,
    calificacion INTEGER NOT NULL,
    comentario TEXT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido)
);

-- Resenia para productos
CREATE TABLE ReseniaProductos (
    id_resenia INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    id_producto INTEGER NOT NULL,
    calificacion INTEGER NOT NULL,
    comentario TEXT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Sugerencias alimenticias
CREATE TABLE Sugerencias (
    id_sugerencia INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    tipo_dieta TEXT,
    alergias TEXT,
    comentarios TEXT NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Notificaciones
CREATE TABLE Notificaciones (
    id_notificacion INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    id_pedido INTEGER NOT NULL,
    mensaje TEXT NOT NULL,
    fecha DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido)
);
