/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.facundo.literAlura.principal;

import com.facundo.literAlura.model.Autor;
import com.facundo.literAlura.model.DatosAutor;
import com.facundo.literAlura.model.Libro;
import com.facundo.literAlura.model.DatosLibro;
import com.facundo.literAlura.model.RespuestaApi;
import com.facundo.literAlura.repository.AutorRepository;
import com.facundo.literAlura.repository.LibroRepository;
import com.facundo.literAlura.service.ConsumoAPI;
import com.facundo.literAlura.service.ConvierteDatos;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author facuu
 */
public class Principal {

    private final Scanner entrada = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.libroRepository = repository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar un libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado anio
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 ->
                    buscarLibroPorTitulo();
                case 2 ->
                    listarLibros();
                case 3 ->
                    listarAutores();
                case 4 ->
                    listarAutoresVivosPorAnio();
                case 5 ->
                    listarLibrosPorIdioma();
                case 0 ->
                    System.out.println("Cerrando la aplicación...");
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = entrada.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+").toLowerCase());
        System.out.println(json);

        RespuestaApi respuesta = conversor.obtenerDatos(json, RespuestaApi.class);
        if (respuesta != null && !respuesta.resultados().isEmpty()) {
            DatosLibro datosLibro = respuesta.resultados().get(0);
            System.out.println(datosLibro);
            return datosLibro;
        } else {
            System.out.println("No se encontraron resultados para: " + nombreLibro);
            return null;
        }
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datosLibro = getDatosLibro();
        Libro libro = new Libro(datosLibro);
        if (libro.getAutor() != null && libro.getAutor().getNombre() != null) {
            String nombre = libro.getAutor().getNombre().trim();
            Autor autorPersistido = autorRepository.findByNombre(nombre)
                    .orElseGet(() -> autorRepository.save(libro.getAutor()));
            libro.setAutor(autorPersistido);
        }
        libroRepository.save(libro);
        System.out.println(datosLibro);
    }

    private void listarLibros() {
        libros = libroRepository.mostrarLibrosOrdenadosPorNombre();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutores() {
        autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);

    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Indique el año en el que desea ver los autores vivos: ");
        Integer anio = entrada.nextInt();
        List<Autor> autoresFiltrados = autorRepository.autoresVivosPorAnio(anio);
        autoresFiltrados.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        var menu = """
                   Indique el idioma por el cual quiere listar: 
                   1- Ingles
                   2- Español
                   """;
        System.out.println(menu);
        Integer op = entrada.nextInt();
        entrada.nextLine();
        switch (op){
            case 1 -> filtrarIdioma("en");
            case 2 -> filtrarIdioma("es");
                
        }
    }

    private void filtrarIdioma(String idioma) {
        libros = libroRepository.filtrarPorIdioma(idioma);
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

}
