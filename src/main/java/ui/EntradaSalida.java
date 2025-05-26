package ui;

import Common.Constantes;
import domain.Cancion;
import domain.Playlist;
import net.datafaker.idnumbers.SouthAfricanIdNumber;
import net.datafaker.providers.base.Bool;
import resources.ReproductorMP3;
import service.SesionServiceImpl;

import java.util.Scanner;

public class EntradaSalida {
    SesionServiceImpl sesion;

    public EntradaSalida(SesionServiceImpl sesion) {
        this.sesion = sesion;
    }

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


    public void primerMenu() {
        int opcion;
        boolean valido = false;
        do {
            System.out.println(Constantes.BIENVENIDA);
            System.out.println(Constantes.INICIARSESION);
            opcion = EntradaSalida.lectorDeOpcionesNumericas();

            if (opcion == 1) {
                int rol;
                do {
                    System.out.println(Constantes.PIDEROL);
                    rol = EntradaSalida.lectorDeOpcionesNumericas();
                } while (rol < 1 || rol > 3);
                System.out.println(Constantes.PIDEUSERNAME);
                String username = EntradaSalida.lectorDeTexto();
                System.out.println(Constantes.PIDECONTRASEÑA);
                String password = EntradaSalida.lectorDeTexto();
                sesion.crearCuenta(username, password, rol);
                sesion.login(username, password);
                if (guardarcambios()) {
                    sesion.guardarCambiosUsuarios();
                }
                valido = true;

            } else if (opcion == 2) {
                sesion.login();
                if (sesion.getUsuarioActual() != null) {
                    valido = true;
                } else System.out.println(Constantes.USUARIONOENCONTRADO);
            }

        } while (!valido);

    }

    public void mainMenu() {
        int rolusuario = sesion.getUsuarioActual().getRol();
        if (rolusuario == 1) {
            mainMenuUsuario();
        } else if (rolusuario == 2) {
            System.out.println("mainMenuArtista()");
        } else if (rolusuario == 3) {
            mainMenuAdmin();
        }
    }

    public void mainMenuUsuario() {
        int opcion;
        do {
            System.out.println(Constantes.MAINMENU);
            opcion = EntradaSalida.lectorDeOpcionesNumericas();

            switch (opcion) {
                case 1:
                    System.out.println(sesion.mostrarCancionesOrdenadas());
                    sesion.reproducirCancion();
                    break;
                case 2:
                    subMenuPlaylistUsuario();
                    break;
            }
        } while (opcion != 3);
    }

    public void mainMenuAdmin() {
        int opcion;
        boolean fin = false;
        do {
            System.out.println(Constantes.MAINMENUADMIN);
            opcion = EntradaSalida.lectorDeOpcionesNumericas();
            switch (opcion) {
                case 1:
                    subMenuCancionesAdmin();
                    break;
                case 2:
                    System.out.println("Supongamos que este es el menu playlist");
                    break;
                case 3:
                    subMenuUsuariosAdmin();
                    break;
                case 4:
                    fin = true;
                    break;
            }
        } while (!fin);
    }

    public void subMenuCancionesAdmin() {
        int opcion;
        boolean fin = false;
        do {
            //Muestra las canciones que se leyeron  de la bbdd (y  tambien las que se dieron de alta dado el caso)
            System.out.println(sesion.getGestorCanciones().listarCanciones());
            System.out.println(Constantes.SUBMENUCANCIONESADMIN);
            opcion = lectorDeOpcionesNumericas();
            switch (opcion) {


                case 1:
                    System.out.println(Constantes.ESCRIBIRNOMBRECANCION);
                    String nombre = lectorDeTexto();
                    System.out.println(Constantes.ESCRIBIRRUTACANCION);
                    String path = lectorDeTexto();
                    System.out.println(Constantes.ESCRIBIRGENERO);
                    String genero = lectorDeTexto();
                    System.out.println(Constantes.ESCRIBIRAUTORCANCION);
                    String autor = lectorDeTexto();
                    System.out.println(Constantes.ESCRIBIRDURACION);
                    String duracion = lectorDeTexto();
                    System.out.println(Constantes.ESCRIBIRDISCOCANCION);
                    String disco = lectorDeTexto();

                    //Asigna el nombre de la cancion como nombre de disco
                    if (disco.isEmpty()) disco = nombre;
                    if (!nombre.isEmpty() && !path.isEmpty() && !genero.isEmpty() && !autor.isEmpty() && !duracion.isEmpty()) {
                        sesion.darDeAltaCancion(path, nombre, genero, autor, duracion, disco);
                    } else System.out.println(Constantes.ERRORENRESPUESTAS);
                    break;
                case 2:
                    //Vuelve a mostrar las canciones para saber cuál dar de baja
                    System.out.println(sesion.getGestorCanciones().listarCanciones());
                    System.out.println(Constantes.ESCRIBIRNOMBRECANCION);
                    String name = lectorDeTexto();
                    sesion.darDeBajaCancion(name);
                    System.out.println(Constantes.CANCIONDADADEBAJA);
                    break;

                case 3:
                    if (guardarcambios()) {
                        sesion.guardarCambiosCanciones();
                    }
                    fin = true;
                    break;
            }
        } while (!fin);
    }


    public void subMenuPlaylistUsuario() {
        int opcion;
        boolean fin = false;
        do {
            System.out.println(Constantes.SUBMENUPLAYLISTSUSUARIOS);
            opcion = lectorDeOpcionesNumericas();
            switch (opcion) {
                case 1:
                    //Reproduce playlist
                    Playlist p;
                    do {
                        System.out.println(sesion.mostrarPlaylists());
                        System.out.println(Constantes.PLAYLISTAMODIFICAR);
                        int id = lectorDeOpcionesNumericas();
                        p = sesion.encontrarPlaylist(id);
                    }while (p == null);
                    sesion.reproducirPlaylist(p);
                    break;
                case 2:
                    //Crea playlist
                    System.out.println(Constantes.ESCRIBIRNOMBREPLAYLIST);
                    String nombre = lectorDeTexto();
                    sesion.crearPlaylist(nombre);
                    break;
                case 3:
                    //Modifica Playlist
                    boolean salir = false;
                    Playlist pModif;
                    do {
                        System.out.println(sesion.mostrarPlaylists());
                        System.out.println(Constantes.PLAYLISTAMODIFICAR);
                        int id = lectorDeOpcionesNumericas();
                        pModif = sesion.encontrarPlaylist(id);
                    }while (pModif == null);
                    int modificacion;
                    do {
                        System.out.println(Constantes.MODIFICARPLAYLIST);
                        modificacion = lectorDeOpcionesNumericas();
                        switch (modificacion) {
                            case 1:
                                //Muestra y Añade canciones
                                System.out.println(sesion.getGestorCanciones().listarCanciones());
                                System.out.println(Constantes.ESCRIBIRNOMBRECANCION);
                                String busqueda = lectorDeTexto();
                                Cancion cancion = sesion.encontrarCancion(busqueda);
                                pModif.getCanciones().add(cancion);
                                System.out.println(Constantes.CANCIONCREADA);
                                break;
                            case 2:
                                //Elimina Cancion
                                System.out.println(Constantes.ESCRIBIRNOMBRECANCION);
                                String eliminar = lectorDeTexto();
                                Cancion song = sesion.encontrarCancion(eliminar);
                                pModif.getCanciones().remove(song);
                                System.out.println(Constantes.CANCIONDADADEBAJA);
                                break;
                            case 3:
                                salir = true;
                                break;
                        }

                    } while (!salir);


                    break;
                case 4:
                    if (guardarcambios()) {
                        sesion.guardarCambiosPlaylist();
                    }
                    fin = true;
                    break;
            }


        } while (!fin);
    }

    public void subMenuUsuariosAdmin() {
        int opcion;
        boolean fin = false;

        do {
            System.out.println(sesion.getGestorUsuarios().listarUsuarios());
            System.out.println(Constantes.SUBMENUUSUARIOS);
            opcion = lectorDeOpcionesNumericas();
            switch (opcion) {
                case 1:
                    //La unica diferencia con el primer menú es que aquí
                    // NO se inicia sesion en este usuario automaticamente
                    int rol;
                    do {
                        System.out.println(Constantes.PIDEROL);
                        rol = EntradaSalida.lectorDeOpcionesNumericas();
                    } while (rol < 1 || rol > 3);
                    System.out.println(Constantes.PIDEUSERNAME);
                    String username = EntradaSalida.lectorDeTexto();
                    System.out.println(Constantes.PIDECONTRASEÑA);
                    String password = EntradaSalida.lectorDeTexto();
                    sesion.crearCuenta(username, password, rol);
                    break;
                case 2:
                    System.out.println(Constantes.PIDEUSERNAME);
                    String nombre = EntradaSalida.lectorDeTexto();
                    sesion.darBajaCuenta(nombre);
                    break;
                case 3:
                    if (guardarcambios()) {
                        sesion.guardarCambiosUsuarios();
                    }
                    fin = true;
                    break;
            }
        } while (!fin);
    }


    public static boolean guardarcambios() {
        int opcion;
        System.out.println(Constantes.GUARDARCAMBIOS);
        opcion = lectorDeOpcionesNumericas();
        if (opcion == 1) {
            System.out.println(Constantes.CAMBIOSGUARDADOS);
            return true;
        } else {
            System.out.println(Constantes.CAMBIOSNOGUARDADOS);
            return false;
        }
    }

}
