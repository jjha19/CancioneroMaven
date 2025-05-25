package dao;

import domain.Usuario;
import java.util.List;

public interface GestorUsuariosInterface {

    List<Usuario> leerUsuariosDeArchivo(String archivo) throws Exception;

    int crearID();

    Usuario encontrarUsuarioPorNombre(String nombre);

    Usuario encontrarUsuarioPorID(int id);

    int cantidadUsuarios();

    void borrarUsuarioPorID(int id);

    void borrarUsuarioPorNombre(String nombre);

    void darAltaUsuario(String username, String password, int rol);

    void guardarUsuariosEnArchivo();
}
