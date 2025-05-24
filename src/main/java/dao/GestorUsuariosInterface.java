package dao;

import domain.Usuario;
import java.util.ArrayList;

public interface GestorUsuariosInterface {

    ArrayList<Usuario> leerUsuariosDeArchivo(String archivo);


    static int crearID(ArrayList<Usuario> usuarios) {
        return 0;
    }


    boolean encontrarUsuario(Usuario usuario);

    int cantidadUsuarios();

    void borrarUsuarioPorID(int id);

    void borrarUsuarioPorNombre(String nombre);

    void darAltaUsuario(Usuario usuario);

}
