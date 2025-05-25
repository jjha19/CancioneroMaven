package domain;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Playlist {
    private int id;
    private String nombre;
    private String autor;
    private int nroCanciones;
    private ArrayList<Cancion> canciones;

    public Playlist(int id, String nombre, String autor, int nroCanciones, ArrayList<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.nroCanciones = nroCanciones;
        this.canciones = canciones;
    }

    public Playlist() {

    }
}
