package modelo.bussinesslogics;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Nota;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotaDAOTest {
    
    public NotaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testRegistrarNota() {
        System.out.println("registrarNota");
        Nota nota = new Nota();
        nota.setUsuario_id(1);
        nota.setTitulo("Terminar apis");
        nota.setContenido("Terminar el api");
        nota.setLibreta_id(2);
        nota.setPrioridad_id(1);
        NotaDAO instance = new NotaDAO();
        int expResult = 2;
        int result = 0;
        try {
        result = instance.registrarNota(nota);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConsultarNotas() throws Exception {
        System.out.println("consultarNotas");
        NotaDAO instance = new NotaDAO();
        int expResult = 4;
        int result = 0;
        ArrayList<Nota> notas = instance.consultarNotas(1, 2, 0);
        for (Nota nota : notas) {
            result = result + 1;
        }
        assertEquals(expResult, result);
    }
    
        @Test
    public void testActualizarNota() throws Exception {
        System.out.println("actualizarNota");
        Nota nota = new Nota();
        nota.setNota_id(2);
        nota.setUsuario_id(1);
        nota.setTitulo("Terminur");
        nota.setContenido("Terminar el api de blog de notas");
        nota.setLibreta_id(2);
        nota.setPrioridad_id(0);
        NotaDAO instance = new NotaDAO();
        int expResult = 0;
        int result = instance.actualizarNota(nota, "Tarea de red");
        assertEquals(expResult, result);
    }


    @Test
    public void testEliminarNota() throws Exception {
        System.out.println("eliminarNota");
        int nota_id = 10;
        NotaDAO instance = new NotaDAO();
        boolean expResult = false;
        boolean result = instance.eliminarNota(nota_id);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testEliminarNotatrue() throws Exception {
        System.out.println("eliminarNota");
        int nota_id = 4;
        NotaDAO instance = new NotaDAO();
        boolean expResult = true;
        boolean result = instance.eliminarNota(nota_id);
        assertEquals(expResult, result);
    }
}
