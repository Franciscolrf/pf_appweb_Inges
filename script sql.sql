CREATE DATABASE IF NOT EXISTS red_social;
USE red_social;

-- Crear tabla Usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreCompleto VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    avatar VARCHAR(255), 
    direccion VARCHAR(255) NOT NULL, -- Campo de dirección unificado
    fechaNacimiento DATE NOT NULL,
    genero ENUM('masculino', 'femenino', 'otro') NOT NULL,
    tipo ENUM('Admor', 'Normal') NOT NULL
);

-- Crear tabla Posts
CREATE TABLE Posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    contenido TEXT NOT NULL,
    fechaHoraCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fechaHoraEdicion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    anclado BOOLEAN DEFAULT FALSE, -- Indica si es una publicación anclada
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id) ON DELETE CASCADE
);

-- Crear tabla Comentarios
CREATE TABLE Comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    fechaHora DATETIME DEFAULT CURRENT_TIMESTAMP,
    post_id INT,
    usuario_id INT,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id) ON DELETE CASCADE
);

-- Índices adicionales para mejorar rendimiento
CREATE INDEX idx_posts_usuario_id ON Posts(usuario_id);
CREATE INDEX idx_comentarios_post_id ON Comentarios(post_id);
CREATE INDEX idx_comentarios_usuario_id ON Comentarios(usuario_id);

-- Trigger para evitar comentarios en publicaciones ancladas
DELIMITER $$
CREATE TRIGGER before_insert_comentarios
BEFORE INSERT ON Comentarios
FOR EACH ROW
BEGIN
    IF (SELECT anclado FROM Posts WHERE id = NEW.post_id) = TRUE THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden agregar comentarios a publicaciones ancladas.';
    END IF;
END$$
DELIMITER ;
