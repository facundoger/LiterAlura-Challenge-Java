/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.facundo.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

/**
 *
 * @author facuu
 */
public record DatosAutor(
        @JsonAlias("name")String autor,
        @JsonAlias("birth_year")Integer fechaNacimiento,
        @JsonAlias("death_year")Integer fechaFallecimiento) {

}
