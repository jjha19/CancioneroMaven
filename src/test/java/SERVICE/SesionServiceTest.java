package SERVICE;

import dao.GestorUsuarios;
import domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.SesionServiceImpl;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SesionServiceTest {

    @Mock
    private GestorUsuarios gestorUsuarios;

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Test
    void testLogin_UsuarioValido() {
        Usuario user = new Usuario("pepe", "1234", 1);
        when(gestorUsuarios.getUsuarios()).thenReturn(List.of(user));

        Usuario resultado = sesionService.login("pepe", "1234");

        assertNotNull(resultado);
        assertEquals("pepe", resultado.getUsername());
    }

    @Test
    void testLogin_Falla() {
        when(gestorUsuarios.cargarUsuarios()).thenReturn(List.of());

        Usuario resultado = sesionService.login("alguien", "mal");

        assertNull(resultado);
    }

    @Test
    void testHaySesionActiva_CuandoLoginExitoso() {
        Usuario user = new Usuario("pepe", "1234", 1);
        when(gestorUsuarios.cargarUsuarios()).thenReturn(List.of(user));

        sesionService.login("pepe", "1234");

        assertTrue(sesionService.haySesionActiva());

        // Verificamos que se llamó a cargarUsuarios()
        verify(gestorUsuarios, times(1)).cargarUsuarios();
    }

    @Test
    void testHaySesionActiva_CuandoNoHayLogin() {
        assertFalse(sesionService.haySesionActiva());
    }


    @Test
    void testGetUsuarioActual_DespuesDeLogin() {
        Usuario user = new Usuario("laura", "abc", 2);
        when(gestorUsuarios.cargarUsuarios()).thenReturn(List.of(user));

        sesionService.login("laura", "abc");
        Usuario actual = sesionService.getUsuarioActual();

        assertNotNull(actual);
        assertEquals("laura", actual.getUsername());
    }

    @Test
    void testLogout() {
        Usuario user = new Usuario("maria", "clave", 3);
        when(gestorUsuarios.cargarUsuarios()).thenReturn(List.of(user));

        sesionService.login("maria", "clave");
        sesionService.logout();

        assertFalse(sesionService.haySesionActiva());
        assertNull(sesionService.getUsuarioActual());
    }

}


