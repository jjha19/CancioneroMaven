package service;

import domain.Usuario;

public interface SesionService {

    /**
     * Intenta iniciar sesión con el usuario y contraseña provistos.
     * @param username nombre de usuario
     * @param password contraseña
     * @return el Usuario logueado si es válido, null si falla
     */
    Usuario login(String username, String password);

    Usuario login();

    /**
     * Cierra la sesión actual
     */
    void logout();

    /**
     * Devuelve el usuario actualmente logueado, si existe
     * @return Usuario actual o null
     */
    Usuario getUsuarioActual();

    /**
     * Indica si hay un usuario logueado
     */
    boolean haySesionActiva();

    String crearCuenta(String username, String password, int rol);
}

