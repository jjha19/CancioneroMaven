package service;

import Common.Constantes;
import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import dao.GestorPlaylists;
import dao.GestorUsuarios;
import domain.Cancion;
import domain.Playlist;
import domain.Usuario;
import lombok.Data;
import resources.Reproductor;
import resources.ReproductorMP3;
import ui.EntradaSalida;

import java.util.List;
import java.util.Map;


@Data
public class SesionServiceImpl implements SesionService {

    private GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private GestorCanciones gestorCanciones = new GestorCanciones();
    private GestorPlaylists gestorPlaylists = new GestorPlaylists();
    private Usuario usuarioActual;

    public SesionServiceImpl() throws ErrorLecturaArchivo {
    }

    //USUARIOS

    @Override
    public Usuario login(String username, String password) {
        List<Usuario> usuarios = gestorUsuarios.getUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                usuarioActual = u;
                return u;
            }
        }
        return null;
    }

    @Override
    public Usuario login() {
        System.out.println(Constantes.PIDEUSERNAME);
        String username = EntradaSalida.lectorDeTexto();
        System.out.println(Constantes.PIDECONTRASEÑA);
        String password = EntradaSalida.lectorDeTexto();
        List<Usuario> usuarios = gestorUsuarios.getUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                usuarioActual = u;
                return u;
            }
        }
        return null;
    }

    @Override
    public void logout() {
        usuarioActual = null;
    }

    @Override
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    @Override
    public void crearCuenta(String username, String password, int rol) {
        gestorUsuarios.darAltaUsuario(username, password, rol);
    }

    public void darBajaCuenta(int id){
        gestorUsuarios.borrarUsuario(id);
    }
    public void darBajaCuenta(String nombre){
        gestorUsuarios.borrarUsuario(nombre);
    }



    // CANCIONES
    public void reproducirCancion() {
        ReproductorMP3 reproductor = new ReproductorMP3();

        System.out.println(Constantes.PREGUNTARPORCANCION);
        String request = EntradaSalida.lectorDeTexto();
        reproductor.reproducirCancion(gestorCanciones.encontrarCancion(request));
    }

    public void darDeAltaCancion(String path, String nombre, String genero, String autor, String duracion, String disco) {
        gestorCanciones.darDeAltaCancion(path, nombre, genero, autor, duracion, disco);
    }

    public void darDeBajaCancion(int id){
        gestorCanciones.darDeBajaCancion(id);
    }
    public void darDeBajaCancion(String nombre){
        gestorCanciones.darDeBajaCancion(nombre);
    }

    public void guardarCambiosUsuarios(){
        gestorUsuarios.guardarUsuariosEnArchivo();
    }

    public void guardarCambiosCanciones(){
        gestorCanciones.guardarCancionesEnArchivo();
    }

    public String mostrarCancionesOrdenadas(){
       return gestorCanciones.mostrarCancionesOrdenadas();
    }

    public Cancion encontrarCancion(String nombre){
        return gestorCanciones.encontrarCancion(nombre);
    }

    // PLAYLISTS

    public void crearPlaylist(String nombre){
        gestorPlaylists.agregarPlaylist(nombre);
    }

    public void guardarCambiosPlaylist(){
        gestorPlaylists.guardarCambiosPlaylists();
    }

    public String mostrarPlaylists(){
        return gestorPlaylists.getPlaylistsJson();
    }
    public Playlist encontrarPlaylist(int id){
        return gestorPlaylists.getPlaylistPorId(id);
    }

    public void reproducirPlaylist(Playlist p){
        ReproductorMP3 reproductor = new ReproductorMP3();
        reproductor.reproducirPlaylist(p);
    }

}
