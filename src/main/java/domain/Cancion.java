package domain;

import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;

public class Cancion {

    private int id;
    private String path;
    private String nombre;
    private String genero;
    private String autor;
    private String duracion;
    private String disco;


    public Cancion(int id, String path, String nombre, String genero, String autor, String duracion, String disco) {
        this.id = id;
        this.path = path;
        this.nombre = nombre;
        this.genero = genero;
        this.autor = autor;
        this.duracion = duracion;
        this.disco = disco;
    }

    public Cancion() throws ErrorLecturaArchivo {
        GestorCanciones g = new GestorCanciones();
        g.leerCancionesDeArchivo().getFirst();
    }


    //Getters y setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPath() { return path; }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    @Override
    public String toString() {
        return id + ";" + path + ";" + nombre + ";" + genero + ";" + autor + ";" + duracion + ";" + disco;
    }

    public String listarCancion() {
        return id + " - " + nombre + " - " + autor + " - " + genero;
    }
}
