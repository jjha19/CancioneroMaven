package dao;

import domain.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GestorUsuarios implements GestorCancionesInterface{
    public static ArrayList<Usuario> leerUsuariosDeArchivo(String archivo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String username = datos[1];
                String password = datos[2];
                String fecha = datos[3];
                int rol = Integer.parseInt(datos[4]);
                Usuario u = new Usuario(id,username,password,fecha,rol);
                usuarios.add(u);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }


    public static int crearID(ArrayList<Usuario> usuarios) {
        int lastID = 1;
        if (!usuarios.isEmpty()) {
            return lastID = usuarios.getLast().getId() + 1;
        }else return lastID;
    }
}
