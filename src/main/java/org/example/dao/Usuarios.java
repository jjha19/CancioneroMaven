package dao;

import domain.Usuario;

import java.util.ArrayList;

public class Usuarios {
    private ArrayList<Usuario> usuarios;
    public Usuarios() {
        usuarios = GestorUsuarios.leerUsuariosDeArchivo("src/dao/bbdd_usuarios.txt");
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
