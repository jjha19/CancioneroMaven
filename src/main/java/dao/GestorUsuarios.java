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

    private static String archivodeusuarios = "src/main/java/dao/bbdd_usuarios.txt";
    ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = leerUsuariosDeArchivo(archivodeusuarios);
    }

    public GestorUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public GestorUsuarios(String archivodeusuarios) {
        this.archivodeusuarios = archivodeusuarios;
    }


    /* public void darAltaUsuario(String username, String password, int rol) {
        usuarios.add(new Usuario(GestorUsuariosInterface.crearID(usuarios), username, password, LocalDate.now(), rol));
    }

     */




        public List<Usuario> cargarUsuarios() {
            List<Usuario> usuariosLeidos = new ArrayList<>();
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
                    usuariosLeidos.add(u);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return usuariosLeidos;
        }


    @Override
    public ArrayList<Usuario> leerUsuariosDeArchivo(String archivo) {
        return null;
    }

    public static int crearID(ArrayList<Usuario> usuarios) {
        int lastID = 1;
        if (!usuarios.isEmpty()) {
            return usuarios.getLast().getId() + 1;
        } else return lastID;
    }

    @Override
    public boolean encontrarUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public int cantidadUsuarios() {
        return 0;
    }

    @Override
    public void borrarUsuarioPorID(int id) {

    }

    @Override
    public void borrarUsuarioPorNombre(String nombre) {

    }

    @Override
    public void darAltaUsuario(Usuario usuario) {

    }

}
