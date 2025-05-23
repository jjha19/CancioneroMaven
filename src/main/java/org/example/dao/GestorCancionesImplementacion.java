package dao;

import domain.Cancion;
import domain.Usuario;

import java.io.*;
import java.util.ArrayList;


public class GestorCanciones {

    public static ArrayList<Cancion> leerCancionesDeArchivo(String archivo) {
        ArrayList<Cancion> canciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(";");
                if (partes.length == 7) {
                    int id = Integer.parseInt(partes[0]);
                    String path = partes[1];
                    String nombre = partes[2];
                    String genero = partes[3];
                    String autor = partes[4];
                    String duracion = partes[5];
                    String disco = partes[6];

                    Cancion c = new Cancion(id, path, nombre, genero, autor, duracion, disco);
                    canciones.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return canciones;
    }

    public static void agregarCancionAlArchivo(Cancion cancion, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(cancion.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public int crearID(ArrayList<Cancion> canciones) {
        int lastID = canciones.getLast().getId();
        return lastID + 1;
    }
}
