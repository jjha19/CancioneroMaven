package Common;

public class ErrorLecturaArchivo extends Exception {

    public ErrorLecturaArchivo() {
        super(Constantes.MALABUSQUEDA);
    }

    public ErrorLecturaArchivo(String mensaje) {
        super(mensaje);
    }

    public ErrorLecturaArchivo(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}

