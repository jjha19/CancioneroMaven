package Common;

public class ErrorEscrituraArchivo extends RuntimeException {
  public ErrorEscrituraArchivo() {
    super(Constantes.ARCHIVOGUARDADOMAL);
  }
    public ErrorEscrituraArchivo(String message) {
        super(message);
    }
}
