package domain;

import dao.GestorUsuarios;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private LocalDate fechaperfil;
    private int rol;
    private ArrayList<Playlist> playlists;

    /*   ROLES:
    - 1: Usuario Común
    - 2: Artista
    - 3: Administrador
     */




    public Usuario(int id, String username, String password, LocalDate fechaperfil, int rol) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.fechaperfil = fechaperfil;
        this.rol = rol;
        this.playlists = null;
    }

    public Usuario(int id, String username, String password, String fechaperfil, int rol) {
        LocalDate fecha = LocalDate.parse(fechaperfil);
        this.id = id;
        this.username = username;
        this.password = password;
        this.fechaperfil = fecha;
        this.rol = rol;
        this.playlists = null;
    }

    public Usuario(String username, String password, int rol) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.fechaperfil = LocalDate.now();
        this.playlists = null;
    }

    //Getters Y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaperfil() {
        return fechaperfil;
    }

    public void setFechaperfil(LocalDate fechaperfil) {
        this.fechaperfil = fechaperfil;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}


class Artista extends Usuario{
    private ArrayList<Cancion> canciones;
    public Artista(int id, String username, String password, LocalDate fecha, int rol, ArrayList<Cancion> cancionespropias) {
        super(id, username, password,fecha, rol);
        this.canciones = cancionespropias;
    }
}
