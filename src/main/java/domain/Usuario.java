package domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
@Data
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

    public String getFechaRegistro(){
        return fechaperfil.toString();
    }

    public String listarUsuario(){
        return "ID: " + id + " | User: " + username + " | Rol: " + rol + " | Fecha de alta: " + fechaperfil;
    }
}


//class Artista extends Usuario{
//    private ArrayList<Cancion> canciones;
//    public Artista(int id, String username, String password, LocalDate fecha, int rol, ArrayList<Cancion> cancionespropias) {
//        super(id, username, password,fecha, rol);
//        this.canciones = cancionespropias;
//    }
//}
