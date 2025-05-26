package ui;

import service.SesionServiceImpl;


public class Main {
    public static void main(String[] args){
        try {

            SesionServiceImpl sesion = new SesionServiceImpl();
            EntradaSalida es = new EntradaSalida(sesion);
            es.primerMenu();
            es.mainMenu();
            System.out.println("Chau chau adios!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}