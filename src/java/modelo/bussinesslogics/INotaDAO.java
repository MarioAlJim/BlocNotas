package modelo.bussinesslogics;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Nota;

public interface INotaDAO {
    
    public ArrayList<Nota> consultarNotas(int usiario_id, int libreta_id, int prioridad_id) throws SQLException;
    public int registrarNota(Nota nota) throws SQLException;
    public int actualizarNota(Nota nota, String titulo) throws SQLException;
    public boolean eliminarNota(int nota_id) throws SQLException;
}
