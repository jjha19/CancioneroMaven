package dao;

import Common.Constantes;
import Common.ErrorLecturaArchivo;
import domain.Cancion;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

@Data
public class GestorCanciones {
    private static final Logger log = LogManager.getLogger(GestorCanciones.class);
    private ArrayList<Cancion> canciones;
    private String archivodecanciones = "src/main/java/dao/bbdd_canciones.txt";

    public GestorCanciones() throws ErrorLecturaArchivo {
        canciones = leerCancionesDeArchivo();
    }

    public GestorCanciones(ArrayList<Cancion> canciones){
        this.canciones = canciones;
    }

    public GestorCanciones(String archivodecanciones) throws ErrorLecturaArchivo {
        canciones = leerCancionesDeArchivo();
    }

    public GestorCanciones(ArrayList<Cancion> canciones, String archivodecanciones){
        this.canciones = canciones;
        this.archivodecanciones = archivodecanciones;
    }



    public ArrayList<Cancion> leerCancionesDeArchivo() throws ErrorLecturaArchivo {
        canciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivodecanciones))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                canciones.add(lineaACancion(linea));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ErrorLecturaArchivo("No se pudo leer el archivo: " + e.getMessage(), e);
        }
        return canciones;
    }

    public Cancion lineaACancion(String linea) throws ErrorLecturaArchivo {
        String[] partes = linea.split(";");
        if (partes.length != 7) {
            throw new ErrorLecturaArchivo("Línea mal formada: " + linea);
        }
        try {
            int id = Integer.parseInt(partes[0]);
            String path = partes[1];
            String nombre = partes[2];
            String genero = partes[3];
            String autor = partes[4];
            String duracion = partes[5];
            String disco = partes[6];
            return new Cancion(id, path, nombre, genero, autor, duracion, disco);
        } catch (NumberFormatException e) {
            throw new ErrorLecturaArchivo("ID inválido en la línea: " + linea, e);
        }
    }

    public boolean agregarCancionAlArchivo(Cancion cancion, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(cancion.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            // No se lanza excepción porque el método devuelve false ante errores
            return false;
        }
    }

    public static int crearID(ArrayList<Cancion> canciones) {
        if (canciones == null || canciones.isEmpty()) return 1;
        return canciones.getLast().getId() + 1;
    }

    public String listarCanciones() {
        StringBuilder songs = new StringBuilder();
        for (Cancion cancion : canciones) {
            songs.append(cancion.listarCancion()).append("\n");
        }
        return songs.toString();
    }

    public Cancion encontrarCancion(String nombreCancion) {
        for (Cancion c : canciones) {
            if (c.getNombre().equalsIgnoreCase(nombreCancion)) {
                return c;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder songs = new StringBuilder();
        for (Cancion cancion : canciones) {
            songs.append(cancion.toString()).append("\n");
        }
        return songs.toString();
    }

    public void setArchivoDeCanciones(String archivo) {
        this.archivodecanciones = archivo;
    }
}
