package ui;

import Common.Constantes;
import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import dao.GestorUsuarios;
import domain.Usuario;
import service.SesionServiceImpl;


public class Main {
    public static void main(String[] args) throws ErrorLecturaArchivo {
        GestorUsuarios gu = new GestorUsuarios();
        GestorCanciones gc = new GestorCanciones();
        SesionServiceImpl sesion = new SesionServiceImpl(gu);

    }
}