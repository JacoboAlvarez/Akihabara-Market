DROP USER 'Akihabara_JAD'@'localhost';
CREATE USER 'Akihabara_JAD'@'localhost' IDENTIFIED BY 'campusfp';
GRANT ALL PRIVILEGES ON *.* TO 'Akihabara_JAD'@'localhost'WITH GRANT OPTION;
FLUSH PRIVILEGES;


DROP DATABASE IF EXISTS  AkihabaraDB;
CREATE DATABASE AkihabaraDB;
USE AkihabaraDB;

CREATE TABLE productos(
id INT PRIMARY KEY auto_increment,
nombre VARCHAR(255) NOT NULL,
categoria VARCHAR(40),
-- EJEMPLO "Figura", "Manga", "Póster", "Llavero", "Ropa")
precio decimal (10,2),
stock int
);

	

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Figura de acción de Goku', 'Figuras', 29.99, 15);

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Poster de Evangelion', 'Posters', 12.50, 30);

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Camiseta de One Piece', 'Ropa', 19.99, 20);

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Taza de My Hero Academia', 'Taza', 12.99, 25);

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Sudadera de Demon Slayer', 'Ropa', 34.90, 10);

INSERT INTO productos (nombre, categoria, precio, stock)
VALUES ('Llavero de Jujutsu Kaisen', 'Llavero', 5.99, 40);

Select * from productos;

DROP TABLE clientes ;
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro DATE
);

-- CREAMOS EL GENERADO PARA LA FECHA DE LOS CLIENTE QUE SE QUEDE CON FORMATO DE LA FECHA
DELIMITER //

CREATE TRIGGER before_clientes_insert
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
    SET NEW.fecha_registro = CURRENT_DATE;
END; //

DELIMITER ;

INSERT INTO clientes (nombre, email, telefono) VALUES ('Juan Pérez', 'juan.perez@example.com', '555-1234');
INSERT INTO clientes (nombre, email, telefono) VALUES ('María Gómez', 'maria.gomez@example.com', '555-5678');

SELECT * FROM clientes;
