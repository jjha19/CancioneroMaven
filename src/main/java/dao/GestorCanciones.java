package dao;

import Common.Constantes;
import Common.ErrorEscrituraArchivo;
import Common.ErrorLecturaArchivo;
import domain.Cancion;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

@Data
public class GestorCanciones {
    private static final Logger log = LogManager.getLogger(GestorCanciones.class);
    private ArrayList<Cancion> canciones;
    private String archivodecanciones = "src/main/java/dao/bbdd_canciones.txt";
    private String archivoBackupBianrio = "src/main/java/dao/backup_canciones.dat";




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
        } catch (IOException archivonoencontrado) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoBackupBianrio))) {
                canciones = (ArrayList<Cancion>) ois.readObject();
                System.out.println(Constantes.LECTURABACKUPBIEN);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(Constantes.LECTURABACKUPMAL);
            }
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
            // No se lanza excepción porque el metodo devuelve false ante errores
            return false;
        }
    }


    public void guardarCancionesEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivodecanciones))) {
            for (Cancion c : canciones) {
                String linea = c.getId() + ";" + c.getPath() + ";" + c.getNombre() + ";" + c.getGenero() + ";" + c.getAutor() + ";" + c.getDuracion() + ";" + c.getDisco();
                bw.write(linea);
                bw.newLine();
            }


        } catch (ErrorEscrituraArchivo e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ARCHIVOGUARDADOMAL);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoBackupBianrio))) {
            oos.writeObject(canciones);
            System.out.println(Constantes.ESCRIBIRBACKUPBIEN);
        } catch (IOException e) {
            System.out.println(Constantes.ESCRIBIRBACKUPMAL);
        }

        System.out.println(Constantes.ARCHIVOGUARDADOBIEN);
    }

    public int crearID() {
        if (canciones == null || canciones.isEmpty()) {
            return 1;
        }
        return canciones.get(canciones.size() - 1).getId() + 1;
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

    public boolean darDeAltaCancion(String path, String nombre, String genero, String autor, String duracion, String disco) {
        Cancion cancion = new Cancion(crearID(),path,nombre,genero,autor,duracion,disco);
        return canciones.add(cancion);

    }


    public boolean darDeBajaCancion(int id) {
        if (canciones.stream().anyMatch(u -> u.getId() == id)) {
            return canciones.remove(canciones.stream().filter(u -> u.getId() == id).findFirst().get());
        }else return false;
    }

    public boolean darDeBajaCancion(String nombre) {
        if (canciones.stream().anyMatch(u -> u.getNombre().equalsIgnoreCase(nombre))) {
             return canciones.remove(canciones.stream().filter(u -> u.getNombre().equalsIgnoreCase(nombre)).findFirst().get());
        }else return false;
    }

    public String mostrarCancionesOrdenadas(){

        //Este metodo por defecto las ordena alfabeticamente agrupándolas por disco y luego por título de cancion
        Comparator<Cancion> ordenadorDiscoNombre = Comparator
                .comparing(Cancion::getDisco, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Cancion::getNombre, String.CASE_INSENSITIVE_ORDER);
        canciones.sort(ordenadorDiscoNombre);
        return listarCanciones();
    }

}
