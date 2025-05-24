package dao;

import Common.Constantes;
import domain.Usuario;
import ui.EntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class GestorUsuarios implements GestorCancionesInterface{

    public static Usuario gestionarUsuario(int opcion, ArrayList<Usuario> usuarios) {
        Usuario user = null;
        GestorUsuarios gu = new GestorUsuarios();
        if (opcion == 1) {
            System.out.println(Constantes.PIDEROL);
            int rol = EntradaSalida.lectorDeOpcionesNumericas();
            System.out.println(Constantes.PIDEUSERNAME);
            String username = EntradaSalida.lectorDeTexto();
            System.out.println(Constantes.PIDECONTRASEÑA);
            String password = EntradaSalida.lectorDeTexto();

            user = new Usuario(dao.GestorUsuarios.crearID(usuarios),username,password, LocalDate.now(),rol);
        } else if (opcion==2) {
            System.out.println(Constantes.PIDEUSERNAME);
            String username = EntradaSalida.lectorDeTexto();

            Optional<Usuario> usuarioexistente = gu.leerUsuariosDeArchivo("src/dao/bbdd_usuarios.txt").stream()
                    .filter(u -> u.getUsername().equals(username)).findFirst();
            if (usuarioexistente.isPresent()) {
                System.out.println(Constantes.PIDECONTRASEÑA);
                String password = EntradaSalida.lectorDeTexto();
                if (usuarioexistente.get().getPassword().equals(password)) {
                    user = usuarioexistente.get();
                }else System.out.println(Constantes.CONTRASENAINCORRECTA);
            }else System.out.println(Constantes.USUARIONOENCONTRADO);
        }
        return user;
    }

    public ArrayList<Usuario> leerUsuariosDeArchivo(String archivo) {
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
            return usuarios.getLast().getId() + 1;
        }else return lastID;
    }


}
