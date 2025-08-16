/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.facundo.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author facuu
 */
public record DatosLibro(
        @JsonAlias("title")String titulo,
        @JsonAlias("languages")List<String> idioma,
        @JsonAlias("download_count")Integer nroDescargas,
        @JsonAlias("authors") List<DatosAutor> autores) {}
