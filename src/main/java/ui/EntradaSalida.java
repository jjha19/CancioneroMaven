package ui;

import Common.Constantes;

import java.util.Scanner;

public class EntradaSalida {
    public static int lectorDeOpcionesNumericas() {
        Scanner lector = new Scanner(System.in);
        boolean valido = false;
        int opcion = 0;
        do {
            try {
                opcion = lector.nextInt();
                lector.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println(Constantes.FORMATOERRONEO);
                lector.nextLine();
            }
        } while (!valido);
        return opcion;
    }

    public static String lectorDeTexto() {
        Scanner lector = new Scanner(System.in);
        boolean valido = false;
        String texto = lector.nextLine();
        if (texto.isEmpty()) {
            System.out.println(Constantes.FORMATOERRONEO);
        } else return texto;
        return texto;
    }

    /*
    public static void mainMenu() {
        Scanner lector = new Scanner(System.in);
        boolean valido = false;
        int opcion = 0;

        do {
            System.out.println(Constantes.MAINMENU);
            opcion = EntradaSalida.lectorDeOpcionesNumericas();
            ReproductorMP3 reproductor = new ReproductorMP3();
            ArrayList<Cancion> canciones = ;
            switch (opcion) {
                case 1:
                    System.out.println(canciones.listarCanciones());
                    System.out.println(Constantes.PREGUNTARPORCANCION);
                    String request = EntradaSalida.lectorDeTexto();
                    reproductor.reproducirCancion(canciones.encontrarCancion(request));
                    break;
                case 2:
                    System.out.println("Este todavía me falta hacerlo");
                    break;
            }
        } while (opcion != 3);

    }
    */

    public void iniciarSesion(){
        System.out.println(Constantes.PIDEUSERNAME);
        String username = EntradaSalida.lectorDeTexto();
        System.out.println(Constantes.PIDECONTRASEÑA);
        String password = EntradaSalida.lectorDeTexto();


    }


}
