package dao;

import domain.Cancion;
import java.io.FileInputStream;
import javazoom.jl.player.Player;



public class ReproductorMP3 implements Reproductor {
    private Player player;
    @Override
    public void reproducirCancion(Cancion cancion) {
        try {
            FileInputStream fis = new FileInputStream(cancion.getPath());
            player = new Player(fis);

            System.out.println("🎵 Reproduciendo: " + cancion.getNombre() + " - " + cancion.getAutor());
            player.play(); // Este método es bloqueante

        } catch (Exception e) {
            System.out.println("Error al reproducir la canción: " + e.getMessage());
        }
    }

}
