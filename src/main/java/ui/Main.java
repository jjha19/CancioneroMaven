package ui;


import Common.ErrorLecturaArchivo;
import service.SesionServiceImpl;


public class Main {
    public static void main(String[] args) throws ErrorLecturaArchivo {
        SesionServiceImpl sesion = new SesionServiceImpl();
        EntradaSalida es = new EntradaSalida(sesion);
        es.primerMenu();
        es.mainMenu();
        System.out.println("Chau chau adios!");

    }
}