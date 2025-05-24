package service;

import Common.Constantes;
import dao.GestorUsuarios;
import domain.Usuario;
import ui.EntradaSalida;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class SesionImplementacion {
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

            Optional<Usuario> usuarioexistente = dao.GestorUsuariosInterface.leerUsuariosDeArchivo("src/dao/bbdd_usuarios.txt").stream()
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
}
