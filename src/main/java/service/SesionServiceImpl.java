package service;

import Common.Constantes;
import dao.GestorUsuarios;
import domain.Usuario;
import ui.EntradaSalida;

import java.util.List;

public class SesionServiceImpl implements SesionService {

    private final GestorUsuarios gestorUsuarios;
    private Usuario usuarioActual;

    public SesionServiceImpl(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
    }

    @Override
    public Usuario login(String username, String password) {
        List<Usuario> usuarios = gestorUsuarios.getUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                usuarioActual = u;
                return u;
            }
        }
        return null;
    }

    @Override
    public void logout() {
        usuarioActual = null;
    }

    @Override
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    @Override
    public void crearCuenta() {
        System.out.println(Constantes.PIDEROL);
        int rol = EntradaSalida.lectorDeOpcionesNumericas();
        System.out.println(Constantes.PIDEUSERNAME);
        String username = EntradaSalida.lectorDeTexto();
        System.out.println(Constantes.PIDECONTRASEÑA);
        String password = EntradaSalida.lectorDeTexto();
    }
}
