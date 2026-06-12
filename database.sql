-- Script para crear la base de datos del blog
CREATE DATABASE IF NOT EXISTS blog_db;
USE blog_db;

-- Las tablas se crearán automáticamente por JPA/Hibernate
-- Este script es solo para referencia de la estructura

-- Tabla Author
-- CREATE TABLE author (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL
-- );

-- Tabla Blog Post
-- CREATE TABLE blog_post (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     author_id BIGINT,
--     title VARCHAR(255) NOT NULL,
--     post TEXT NOT NULL,
--     FOREIGN KEY (author_id) REFERENCES author(id)
-- );

