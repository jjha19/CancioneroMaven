package DAO;

import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import domain.Cancion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.*;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class GestorCancionesTest {

    private static final Logger log = LogManager.getLogger(GestorCancionesTest.class);
    private GestorCanciones gestor;
    private final String testFile = "src/test/resources/test_canciones.txt";

    @BeforeEach
    void setUp() throws IOException, ErrorLecturaArchivo {
        // Crear la carpeta si no existe
        File resourcesFolder = new File("src/test/resources");
        if (!resourcesFolder.exists()) {
            resourcesFolder.mkdirs(); // crea todas las carpetas necesarias
        }

        // Crear archivo de prueba
        File archivoTest = new File("src/test/resources/test_canciones.txt");
        FileWriter writer = new FileWriter(archivoTest);
        writer.write("1,Shape of You,3.53,Ed Sheeran,Pop,Divide\n");
        writer.write("2,Blinding Lights,3.20,The Weeknd,Synthwave,After Hours\n");
        writer.write("3,Bohemian Rhapsody,5.55,Queen,Rock,A Night at the Opera\n");
        writer.close();

        // Inicializar el gestor con el archivo
        gestor = new GestorCanciones(archivoTest.getPath());
    }


    @AfterEach
    void tearDown() {
        // Eliminar el archivo después del test
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
    }



    @Nested
    class Nest {

        private GestorCanciones gestor;

        @BeforeEach
        void setUp() throws ErrorLecturaArchivo {
            gestor = new GestorCanciones();
        }

        @Test
        @Order(1)
        void testLeerCancionesDeArchivo_OK() throws ErrorLecturaArchivo {
            ArrayList<Cancion> canciones = gestor.leerCancionesDeArchivo();
            assertNotNull(canciones);
            assertEquals(2, canciones.size());
            assertEquals("Caminito", canciones.get(0).getNombre());
        }

        @Test
        @Order(2)
        void testLeerCancionesDeArchivo_ErrorArchivoNoExiste() {
            // Cambiamos el archivo a uno que no existe para este test
            gestor.setArchivodecanciones("archivo_que_no_existe.txt");

            assertThrows(ErrorLecturaArchivo.class, () -> gestor.leerCancionesDeArchivo());
        }
    }


    // Test 7 - LeercancionesLienaMala() Hay una linea sin 7 campos exactamente
    @Test
    @Order(7)
    void testLeerCancionesDeArchivo_ErrorLineaMala() {
        gestor.setArchivodecanciones("src/main/java/dao/TestLineaMala.txt");
        assertThatThrownBy(() -> gestor.leerCancionesDeArchivo())
                .isInstanceOf(Common.ErrorLecturaArchivo.class)
                .hasMessageContaining("Línea mal formada");
    }



    // Test 5 - agregarCancionAlArchivo() correcto
    @Test
    @Order(5)
    void testAgregarCancionAlArchivo_OK() {
        Cancion nueva = new Cancion(3, "nuevo.mp3", "NuevaCancion", "Jazz", "NuevoAutor", "02:45", "NuevoDisco");
        boolean resultado = gestor.agregarCancionAlArchivo(nueva, testFile);
        assertTrue(resultado);
    }

    // Test 6 - agregarCancionAlArchivo() error en archivo
    @Test
    @Order(6)
    void testAgregarCancionAlArchivo_Error() {
        Cancion nueva = new Cancion(4, "otro.mp3", "OtraCancion", "Pop", "OtroAutor", "03:20", "OtroDisco");

        // Intentar escribir en un archivo inválido
        boolean resultado = gestor.agregarCancionAlArchivo(nueva, "/ruta/imposible/test.txt");
        assertFalse(resultado);
    }

    // Test 3 - encontrarCancion() exitoso
    @Test
    @Order(3)
    void testEncontrarCancion_OK() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        canciones.add(new Cancion(1, "p.mp3", "Hola", "Trap", "A", "01:01", "D"));
        gestor.setCanciones(canciones);

        Cancion encontrada = gestor.encontrarCancion("Hola");
        assertNotNull(encontrada);
        assertEquals("Hola", encontrada.getNombre());
    }

    // Test 4 - encontrarCancion() no existe
    @ParameterizedTest
    @ValueSource(strings = {"Inexistente", "Desconocida", "SinNombre"})
    void testEncontrarCancion_NombreNoEncontrado(String nombreBuscado) {
        log.info("Se ha efectuado la prueba parametrizada");
        ArrayList<Cancion> canciones = new ArrayList<>();
        canciones.add(new Cancion(1, "a.mp3", "Hola", "Trap", "A", "01:01", "D"));
        canciones.add(new Cancion(2, "b.mp3", "CancionX", "Rock", "B", "02:02", "E"));
        gestor.setCanciones(canciones);

        Cancion encontrada = gestor.encontrarCancion(nombreBuscado);
        assertNull(encontrada);
    }
}
