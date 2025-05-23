package Common;

public class ExcepcionContrasenaIncorrecta extends Exception {
    public ExcepcionContrasenaIncorrecta() {
        super(Constantes.CONTRASENAINCORRECTA);
    }
}
