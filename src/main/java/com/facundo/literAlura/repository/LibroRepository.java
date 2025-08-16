/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.facundo.literAlura.repository;

import com.facundo.literAlura.model.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author facuu
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l ORDER BY l.titulo ASC")
    List<Libro> mostrarLibrosOrdenadosPorNombre();
    
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> filtrarPorIdioma(String idioma);    
}
