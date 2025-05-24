package dao;

import domain.Usuario;
import java.util.ArrayList;

public interface GestorUsuariosInterface {

    ArrayList<Usuario> leerUsuariosDeArchivo(String archivo);


    int crearID(ArrayList<Usuario> usuarios);


    boolean encontrarUsuario(Usuario usuario);

    int cantidadUsuarios();

    void borrarUsuarioPorID(int id);

    void borrarUsuarioPorNombre(String nombre);

    void darAltaUsuario(Usuario usuario);

}
