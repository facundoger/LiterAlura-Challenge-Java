/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.facundo.literAlura.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author facuu
 */
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Integer nroDescargas;
    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Autor creador;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        if (datosLibro.idioma() != null && !datosLibro.idioma().isEmpty()) {
            this.idioma = datosLibro.idioma().get(0);
        } else {
            this.idioma = null;
        }
        this.nroDescargas = datosLibro.nroDescargas();
        if (datosLibro.autores() != null && !datosLibro.autores().isEmpty()) {
            this.creador = new Autor(datosLibro.autores().get(0));
        } else {
            this.creador = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return creador;
    }

    public void setAutor(Autor autor) {
        this.creador = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNroDescargas() {
        return nroDescargas;
    }

    public void setNroDescargas(Integer nroDescargas) {
        this.nroDescargas = nroDescargas;
    }

    @Override
    public String toString() {
        return "titulo=" + titulo
                + ", idioma=" + idioma
                + ", nroDescargas=" + nroDescargas
                + ", autor=" + creador.getNombre();
    }

}
