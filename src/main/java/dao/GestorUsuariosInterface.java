package dao;

import domain.Usuario;
import java.util.ArrayList;

public interface GestorUsuariosInterface {

     ArrayList<Usuario> leerUsuariosDeArchivo(String archivo);


     int crearID(ArrayList<Usuario> usuarios);

}
