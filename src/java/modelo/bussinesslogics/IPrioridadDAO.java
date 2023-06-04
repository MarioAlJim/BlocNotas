package modelo.bussinesslogics;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Prioridad;

public interface IPrioridadDAO {
    public ArrayList<Prioridad> consultarPrioridades() throws SQLException;
}
