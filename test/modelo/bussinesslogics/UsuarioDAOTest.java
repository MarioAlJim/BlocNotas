package modelo.bussinesslogics;

import modelo.pojos.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }

    @Test
    public void testActivarUsuario() throws Exception {
        System.out.println("activarUsuario");
        Usuario usuario = new Usuario();
        usuario.setCelular("2554769856");
        usuario.setOtp("741258");
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = true;
        boolean result = instance.activarUsuario(usuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        Usuario usuario = new Usuario();
        usuario.setCelular("2554769856");
        usuario.setContrasena("majiji");
        UsuarioDAO instance = new UsuarioDAO();
        Usuario result = instance.login(usuario);
        assertEquals(1, result.getUsuario_id());
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        System.out.println("actualizarUsuario");
        Usuario usuario = new Usuario();
        usuario.setCelular("2554769856");
        usuario.setContrasena("majiji");
        usuario.setNombres("Felix");
        usuario.setApellidos("Hernandez");
        usuario.setUsuario_id(1);
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = true;
        boolean result = instance.actualizarUsuario(usuario);
        assertEquals(expResult, result);
    }
    
}
