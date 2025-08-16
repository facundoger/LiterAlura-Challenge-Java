/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.facundo.literAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author facuu
 */
public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        if (json == null || json.isBlank()) {
            System.out.println("JSON vacío, no se puede convertir.");
            return null;
        }
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConvierteDatos.class.getName()).log(Level.SEVERE, "Error al parsear JSON", ex);
        }
        return null;
    }
    
}
