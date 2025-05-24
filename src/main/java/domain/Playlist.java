package domain;

import java.util.ArrayList;

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
    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNroCanciones() {
        return nroCanciones;
    }

    public void setNroCanciones(int nroCanciones) {
        this.nroCanciones = nroCanciones;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
}
