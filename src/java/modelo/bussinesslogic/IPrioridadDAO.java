/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Prioridad;

public interface IPrioridadDAO {
    public ArrayList<Prioridad> consultarPrioridades() throws SQLException;
}

