# Literalura — Java Challenge

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)  
2. [Demostración de funciones y aplicaciones](#demostración-de-funciones-y-aplicaciones)  
3. [Tecnologías utilizadas](#tecnologías-utilizadas)  
---

## Descripción del Proyecto
**Literalura** es un proyecto de práctica (challenge) desarrollado como parte de la formación *Alura ONE*.  
Es un pequeño gestor y buscador de libros que consume la API pública **Gutendex** para recuperar metadatos de libros, los transforma y los persiste en una base de datos PostgreSQL mediante **Spring Boot**, **Hibernate/JPA** y **Jackson** para el manejo de JSON.

El proyecto ofrece una interfaz de consola con un menú de opciones para buscar libros por título, listar libros y autores almacenados, filtrar autores vivos por año y listar libros por idioma.

---

## Demostración de funciones y aplicaciones

Menú principal:

  1 - Buscar un libro por titulo
  2 - Listar libros registrados
  3 - Listar autores registrados
  4 - Listar autores vivos en un determinado año
  5 - Listar libros por idioma
  
  0 - Salir

---

## Tecnologías utilizadas

  - Java 24
  - Spring Boot (starter web / data JPA)
  - Hibernate / JPA
  - PostgreSQL
  - Jackson (para deserialización JSON)
  - Maven (gestión de dependencias y build)

---
