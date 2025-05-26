package dao;

import Common.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.Playlist;
import lombok.Data;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;



@Data
public class GestorPlaylists {
    private String archivoplaylists = "src/main/java/dao/bbdd_playlists.json";

    private Map<Integer, Playlist> playlists;
    private Gson gson;
    private int proximoId = 1;

    public GestorPlaylists() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.playlists = new HashMap<>();
        cargarPlaylists();
    }

    private void cargarPlaylists() {
        try (FileReader reader = new FileReader(archivoplaylists)) {
            Type listType = new TypeToken<ArrayList<Playlist>>() {}.getType();
            List<Playlist> lista = gson.fromJson(reader, listType);
            if (lista != null) {
                int maxId = 0;
                for (Playlist p : lista) {
                    playlists.put(p.getId(), p);
                    if (p.getId() > maxId) {
                        maxId = p.getId();
                    }
                }
                proximoId = maxId + 1;
            }
        } catch (IOException e) {
            System.out.println("⚠️ No se pudo leer el archivo: " + archivoplaylists + ". Se empezará con una lista vacía.");
        }
    }

    public void guardarCambiosPlaylists() {
        try (FileWriter writer = new FileWriter(archivoplaylists)) {
            List<Playlist> lista = new ArrayList<>(playlists.values());
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar playlists: " + e.getMessage());
        }
    }

    public void agregarPlaylist(String nombre) {
        Playlist playlist = new Playlist(nombre);
        if (playlist.getId() == 0) {
            playlist.setId(proximoId++);
        }
        playlists.put(playlist.getId(), playlist);
        System.out.println(Constantes.PLAYLISTCREADABIEN);
    }

    public Playlist getPlaylistPorId(int id) {
        return playlists.get(id);
    }

    public String getPlaylistsJson() {
        return gson.toJson(playlists);
    }

}

