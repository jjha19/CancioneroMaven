package domain;

import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import lombok.Data;

@Data
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


    @Override
    public String toString() {
        return id + ";" + path + ";" + nombre + ";" + genero + ";" + autor + ";" + duracion + ";" + disco;
    }

    public String listarCancion() {
        return "Nombre: " + nombre + " - Artista: " + autor  + " - ID: " + id + " - Genero: " + genero;
    }
}
