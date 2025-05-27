package Common;

public class Constantes {
    public static final String BIENVENIDA  = "Bienvenido a MiCancionero";
    public static final String INICIARSESION = """
            Quiere:
            1 Crear una cuenta
            2 Iniciar Sesion
            """;

    public static final String PIDEUSERNAME  = "Escriba el nombre de Usuario";
    public static final String PIDECONTRASEÑA  = "Escriba la Contraseña";
    public static final String PIDEROL  = "Elija su Rol:\n" +"* 1: Usuario Común\n"+"* 2: Artista\n"+"* 3: Administrador ";
    public static final String CONTRASENAFORMATOERRONEO  = "La contraseña debe tener entre 6 y 10 caracteres, una minusucula, una mayúscula y un caracter especial";


    public static final String MALABUSQUEDA = "La búsqueda no ha sido exitosa. Pruebe cambiando los parámetros";
    public static final String USUARIONOENCONTRADO = "No se ha encontrado el usuario";
    public static final String CONTRASENAINCORRECTA = "Contraseña Incorrecta. Acceso Denegado.";
    public static final String FORMATOERRONEO = "El formato de respuesta dado no corresponde al esperado. Intenta de nuevo";


    public static final String GUARDARCAMBIOS = """
            Quieres guardar los cambios hechos en la BBDD?
            1 - Si
            2 - No
            """;

    public static final String CAMBIOSGUARDADOS = "Guardando Cambios...";
    public static final String CAMBIOSNOGUARDADOS = "No se guardarán los cambios";

    public static final String ESCRIBIRBACKUPBIEN = "Se ha generado correctamente un backup binario";
    public static final String ESCRIBIRBACKUPMAL = "No se ha podido generar el backup binario";
    public static final String LECTURABACKUPBIEN = "Se ha accedido al backup binario";
    public static final String LECTURABACKUPMAL = "No se ha podido acceder al backup binario";

    public static final String ARCHIVOGUARDADOBIEN = "El archivo se ha guardado correctamente";
    public static final String ARCHIVOGUARDADOMAL = "Ha habido un error al escribir el archivo";


    public static final String MAINMENU = """
            Elige un Sub-menú
            1- Canciones
            2- Playlists
            3- Salir
            """;

    public static final String MAINMENUADMIN = """
            Elige un Sub-menú
            1- Canciones
            2- Playlists
            3- Usuarios
            4- Salir
            """;
    public static final String USUARIOCREADO = "Usuario creado exitosamente";
    public static final String USUARIOELIMINADO = "Usuario eliminado exitosamente";

    public static final String PREGUNTARPORCANCION = "Que cancion quieres poner?";

    public static final String CANCIONCREADA = "Cancion añadida exitosamente";
    public static final String CANCIONDADADEBAJA = "Cancion dada de baja exitosamente";
    public static final String SUBMENUCANCIONESADMIN = """
            Quieres:
            
            1 - Dar de alta una cancion
            2 - Dar de baja una cancion
            3 - Salir
            """;
    public static final String ESCRIBIRNOMBRECANCION = "Por favor escribe el nombre de la cancion";
    public static final String ESCRIBIRNOMBREARCHIVOCANCION = "Por favor escribe el nombre de tu archivo de cancion. (La extension .mp3 se añade sola)";
    public static final String ESCRIBIRGENERO = "Por favor escribe el genero de la cancion";
    public static final String ESCRIBIRAUTORCANCION = "Por favor escribe el artista, intérprete o autor de tu cancion";
    public static final String ESCRIBIRDURACION = "Por favor escribe la duracion de la cancion";
    public static final String ESCRIBIRDISCOCANCION = "Por favor escribe a qué disco pertenece tu cancion. " +
            "\nSi no lo sabes o es un single, solo presiona enter";

    public static final String ERRORENRESPUESTAS = "Las respuestas que diste tienen algún error. Se ha cancelado el proceso";

    public static final String SUBMENUUSUARIOS = """
            Quieres:
            
            1 - Dar de alta un usuario
            2 - Dar de baja un usuario
            3 - Salir
            """;

    public static final String SUBMENUPLAYLISTSUSUARIOS = """
            Quieres:
            1 - Reproducir una playlist
            2 - Crear una playlist
            3 - Modificar una playlist
            4 - Salir
            """;
    public static final String ESCRIBIRNOMBREPLAYLIST = "Por favor escribe el nombre de la playlist";
    public static final String PLAYLISTAMODIFICAR = "Que playlist quieres modificar?\nEscribe el ID";
    public static final String PLAYLISTCREADABIEN = "Playlist creada con éxito";
    public static final String MODIFICARPLAYLIST = """
                                Que quieres modificar en la playlist?
                                1 - Añadir Canciones
                                2 - Eliminar Canciones
                                3 - Salir
                                """;
    public static final String ESCRIBIRIDPLAYLIST = "Escribe el ID de la playlist";
}
