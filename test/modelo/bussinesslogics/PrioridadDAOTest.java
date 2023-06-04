/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bussinesslogics;

import java.util.ArrayList;
import modelo.pojos.Prioridad;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author acarm
 */
public class PrioridadDAOTest {
    
    public PrioridadDAOTest() {
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
    public void testConsultarPrioridades() throws Exception {
        System.out.println("consultarPrioridades");
        PrioridadDAO instance = new PrioridadDAO();
        int expResult = 3;
        ArrayList<Prioridad> prioridades = instance.consultarPrioridades();
        int result = 0;
        for(Prioridad prioridad: prioridades) {
            result++;
        }
        assertEquals(expResult, result);
    }
    
}
