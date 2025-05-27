package DAO;

import Common.ErrorLecturaArchivo;
import dao.GestorCanciones;
import domain.Cancion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class GestorCancionesTest {

    private static final Logger log = LogManager.getLogger(GestorCancionesTest.class);

    @Mock
    ArrayList<Cancion> cancionesMock;

    GestorCanciones gestor;

    private  String testFile = "src/test/resources/test_canciones.txt";


    @BeforeEach
    void setUp() throws IOException, ErrorLecturaArchivo {
        cancionesMock = new ArrayList<>();
        cancionesMock.add(new Cancion(1, "mock.mp3", "Mock", "Pop", "Autor", "3:00", "Disco"));

        gestor = new GestorCanciones(cancionesMock);
        // Crear la carpeta si no existe
        File resourcesFolder = new File("src/test/resources");
        if (!resourcesFolder.exists()) {
            resourcesFolder.mkdirs();
        }

        // Crear archivo de prueba
        File archivoTest = new File(testFile);
        FileWriter writer = new FileWriter(archivoTest);
        writer.write("1,Shape of You,3.53,Ed Sheeran,Pop,Divide\n");
        writer.write("2,Blinding Lights,3.20,The Weeknd,Synthwave,After Hours\n");
        writer.write("3,Bohemian Rhapsody,5.55,Queen,Rock,A Night at the Opera\n");
        writer.close();

        // También podés probar: gestor = new GestorCanciones(testFile);
    }

    @AfterEach
    void tearDown() {
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
            gestor.setArchivodecanciones("archivo_que_no_existe.txt");
            assertThrows(ErrorLecturaArchivo.class, () -> gestor.leerCancionesDeArchivo());
        }
    }

    @Test
    @Order(7)
    void testLeerCancionesDeArchivo_ErrorLineaMala() {
        gestor.setArchivodecanciones("src/main/java/dao/TestLineaMala.txt");
        assertThatThrownBy(() -> gestor.leerCancionesDeArchivo())
                .isInstanceOf(ErrorLecturaArchivo.class)
                .hasMessageContaining("Línea mal formada");
    }

    @Test
    @Order(5)
    void testAgregarCancionAlArchivo_OK() throws ErrorLecturaArchivo {
        Cancion nueva = new Cancion(3, "nuevo.mp3", "NuevaCancion", "Jazz", "NuevoAutor", "02:45", "NuevoDisco");
        gestor = new GestorCanciones(testFile);  // usamos el archivo real
        boolean resultado = gestor.agregarCancionAlArchivo(nueva, testFile);
        assertTrue(resultado);
    }

    @Test
    @Order(6)
    void testAgregarCancionAlArchivo_Error() throws ErrorLecturaArchivo {
        Cancion nueva = new Cancion(4, "otro.mp3", "OtraCancion", "Pop", "OtroAutor", "03:20", "OtroDisco");
        gestor = new GestorCanciones(testFile);
        boolean resultado = gestor.agregarCancionAlArchivo(nueva, "/ruta/imposible/test.txt");
        assertFalse(resultado);
    }

    @Test
    @Order(3)
    void testEncontrarCancion_OK() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        canciones.add(new Cancion(1, "p.mp3", "Hola", "Trap", "A", "01:01", "D"));
        gestor = new GestorCanciones(canciones);
        Cancion encontrada = gestor.encontrarCancion("Hola");
        assertNotNull(encontrada);
        assertEquals("Hola", encontrada.getNombre());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Inexistente", "Desconocida", "SinNombre"})
    void testEncontrarCancion_NombreNoEncontrado(String nombreBuscado) {
        log.info("Se ha efectuado la prueba parametrizada");
        ArrayList<Cancion> canciones = new ArrayList<>();
        canciones.add(new Cancion(1, "a.mp3", "Hola", "Trap", "A", "01:01", "D"));
        canciones.add(new Cancion(2, "b.mp3", "CancionX", "Rock", "B", "02:02", "E"));
        gestor = new GestorCanciones(canciones);
        Cancion encontrada = gestor.encontrarCancion(nombreBuscado);
        assertNull(encontrada);
    }



    //Metodos Añadidos para que gema me apruebe con su gran corazon <3

    @Test
    void testDarDeAltaCancion_mockeado() {
        boolean resultado = gestor.darDeAltaCancion("ruta.mp3", "Mock", "Pop", "Autor", "3:00", "Disco");

        assertTrue(resultado);
        assertEquals(2, gestor.getCanciones().size());
    }

    @Test
    void testDarDeBajaCancion_mockeado() {
        int id = 123;

        Cancion mockCancion = mock(Cancion.class);
        when(mockCancion.getId()).thenReturn(id);

        // Usamos una lista real con un mock adentro
        ArrayList<Cancion> listaReal = new ArrayList<>();
        listaReal.add(mockCancion);

        // Inyectamos la lista real
        gestor.setCanciones(listaReal);

        boolean resultado = gestor.darDeBajaCancion(id);

        assertTrue(resultado);
        assertFalse(listaReal.contains(mockCancion));
    }

}
