package dao;

import Common.Constantes;
import domain.Usuario;
import ui.EntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorUsuarios implements GestorUsuariosInterface {

    private final String archivodeusuarios = "src/dao/bbdd_usuarios.txt";
    ArrayList<Usuario> usuarios;
    public GestorUsuarios() {
        usuarios = leerUsuariosDeArchivo(archivodeusuarios);
    }

    public static Usuario gestionarUsuario(int opcion, ArrayList<Usuario> usuarios) {
        Usuario user = null;
        GestorUsuarios gu = new GestorUsuarios();
        if (opcion == 1) {



        } else if (opcion == 2) {



    }

    public void darAltaUsuario(String username, String password, int rol) {
        usuarios.add(new Usuario(crearID(usuarios), username, password, LocalDate.now(), rol));
    }




    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivodeusuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String username = datos[1];
                String password = datos[2];
                String fecha = datos[3];
                int rol = Integer.parseInt(datos[4]);
                Usuario u = new Usuario(id, username, password, fecha, rol);
                usuarios.add(u);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }


    public int crearID(ArrayList<Usuario> usuarios) {
        int lastID = 1;
        if (!usuarios.isEmpty()) {
            return usuarios.getLast().getId() + 1;
        } else return lastID;
    }

}
