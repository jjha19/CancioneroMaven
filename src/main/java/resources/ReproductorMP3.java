package resources;

import domain.Cancion;
import java.io.FileInputStream;

import domain.Playlist;
import javazoom.jl.player.Player;



public class ReproductorMP3 implements Reproductor {
    @Override
    public void reproducirCancion(Cancion cancion) {
        try {
            FileInputStream fis = new FileInputStream(cancion.getPath());
            Player player = new Player(fis);

            System.out.println("🎵 Reproduciendo: " + cancion.getNombre() + " - " + cancion.getAutor());
            player.play(); // Este método es bloqueante

        } catch (Exception e) {
            System.out.println("Error al reproducir la canción: " + e.getMessage());
        }
    }

    public void reproducirPlaylist(Playlist playlist) {
        if (playlist.getCanciones() == null || playlist.getCanciones().isEmpty()) {
            System.out.println("La playlist está vacía.");
            return;
        }

        ReproductorMP3 reproductor = new ReproductorMP3();

        System.out.println("▶ Reproduciendo playlist: " + playlist.getNombre());

        for (Cancion cancion : playlist.getCanciones()) {
            reproductor.reproducirCancion(cancion);
        }

        System.out.println("▶ Fin de la playlist: " + playlist.getNombre());
    }
}
