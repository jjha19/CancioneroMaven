package ui;

import Common.Constantes;
import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import dao.GestorUsuarios;
import domain.Usuario;
import resources.ReproductorMP3;
import service.SesionServiceImpl;


public class Main {
    public static void main(String[] args) throws ErrorLecturaArchivo {
        GestorUsuarios gu = new GestorUsuarios();
        GestorCanciones gc = new GestorCanciones();
        SesionServiceImpl sesion = new SesionServiceImpl(gu);
        ReproductorMP3 reproductor = new ReproductorMP3();

        System.out.println(gc.listarCanciones());
        System.out.println(Constantes.PREGUNTARPORCANCION);
        String request = EntradaSalida.lectorDeTexto();
        reproductor.reproducirCancion(gc.encontrarCancion(request));


    }
}