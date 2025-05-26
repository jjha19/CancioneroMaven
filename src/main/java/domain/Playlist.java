package domain;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Playlist {
    private int id = 0;
    private String nombre;
    private ArrayList<Cancion> canciones;

    public Playlist(int id, String nombre, ArrayList<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.canciones = canciones;
    }

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();

    }

}
