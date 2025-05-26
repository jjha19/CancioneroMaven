package dao;

import Common.Constantes;
import Common.ErrorEscrituraArchivo;
import Common.ErrorLecturaArchivo;
import domain.Usuario;
import lombok.Data;



import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class GestorUsuarios implements GestorUsuariosInterface {

    private String archivodeusuarios = "src/main/java/dao/bbdd_usuarios.txt";
    private List<Usuario> usuarios;

    public GestorUsuarios() throws ErrorLecturaArchivo {
        usuarios = leerUsuariosDeArchivo(archivodeusuarios);
    }

    public GestorUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public GestorUsuarios(String nuevoarchivo) throws ErrorLecturaArchivo {
        this.archivodeusuarios = nuevoarchivo;
        this.usuarios = leerUsuariosDeArchivo(nuevoarchivo);
    }


    public void darAltaUsuario(String username, String password, int rol) {
        usuarios.add(new Usuario(crearID(), username, password, LocalDate.now(), rol));
        System.out.println(Constantes.USUARIOCREADO);
    }

    @Override
    public void guardarUsuariosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivodeusuarios))) {
            for (Usuario u : usuarios) {
                String linea = u.getId() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getFechaRegistro() + ";" + u.getRol() + ";" + u.getPlaylists();
                bw.write(linea);
                bw.newLine();
            }
            System.out.println(Constantes.ARCHIVOGUARDADOBIEN);
        } catch (ErrorEscrituraArchivo e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ARCHIVOGUARDADOMAL);
        }
    }

    @Override
    public List<Usuario> leerUsuariosDeArchivo(String archivo) throws ErrorLecturaArchivo {
        usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
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
            throw new ErrorLecturaArchivo();
        }
        return usuarios;
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String nombre) {
        Usuario user = null;
        if(usuarios.stream().anyMatch(u -> u.getUsername().equals(nombre))) {
            user = usuarios.stream().filter(u -> u.getUsername().equals(nombre)).findFirst().orElse(null);
        }else System.out.println(Constantes.USUARIONOENCONTRADO);
        return user;
    }

    @Override
    public Usuario encontrarUsuarioPorID(int id) {
        Usuario user = null;
        if(usuarios.stream().anyMatch(u -> u.getId() == id)) {
            user = usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        }else System.out.println(Constantes.USUARIONOENCONTRADO);
        return user;
    }

    public int crearID() {
        int lastID = 1;
        if (!usuarios.isEmpty()) {
            return usuarios.getLast().getId() + 1;
        } else return lastID;
    }

    @Override
    public int cantidadUsuarios() {
        return usuarios.size();
    }

    @Override
    public void borrarUsuario(int id) {
        if (usuarios.stream().anyMatch(u -> u.getId() == id)) {
            usuarios.remove(usuarios.stream().filter(u -> u.getId() == id).findFirst().get());
        }else System.out.println(Constantes.USUARIONOENCONTRADO);
    }

    @Override
    public void borrarUsuario(String nombre) {
        if (usuarios.stream().anyMatch(u -> u.getUsername().equals(nombre))) {
            usuarios.remove(usuarios.stream().filter(u -> u.getUsername().equals(nombre)).findFirst().get());
            System.out.println(Constantes.USUARIOELIMINADO);
        }else System.out.println(Constantes.USUARIONOENCONTRADO);
    }

    public String listarUsuarios(){
        StringBuilder users = new StringBuilder();
        for (Usuario u : usuarios) {
            users.append(u.listarUsuario() + "\n");
        }
        return users.toString();
    }


}
