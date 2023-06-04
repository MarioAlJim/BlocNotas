package modelo.bussinesslogics;

import java.util.ArrayList;
import modelo.pojos.Libreta;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibretaDAOTest {
    
    public LibretaDAOTest() {
    }

    @Test
    public void testConsultarLibretasDeUsuario() throws Exception {
        System.out.println("consultarLibretasDeUsuario");
        LibretaDAO instance = new LibretaDAO();
        int expResult = 3;
        ArrayList<Libreta> result = instance.consultarLibretasDeUsuario(1);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testRegistrarLibreta() throws Exception {
        System.out.println("registrarLibreta");
        Libreta libreta = new Libreta();
        libreta.setNombre("Pendientes 2");
        libreta.setUsuario_id(1);
        libreta.setColor("125896");
        LibretaDAO instance = new LibretaDAO();
        boolean expResult = false;
        boolean result = instance.registrarLibreta(libreta);
        assertEquals(expResult, result);
    }

    @Test
    public void testActualizarLibreta() throws Exception {
        System.out.println("actualizarLibreta");
        Libreta libreta = new Libreta();
        libreta.setLibreta_id(6);
        libreta.setNombre("tareas");
        libreta.setUsuario_id(1);
        libreta.setColor("125896");
        LibretaDAO instance = new LibretaDAO();
        int expResult = 0;
        int result = instance.actualizarLibreta(libreta);
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminarLibreta() throws Exception {
        System.out.println("eliminarLibreta");
        LibretaDAO instance = new LibretaDAO();
        int result = instance.eliminarLibreta(5);
        assertEquals(0, result);
    }
    
}
