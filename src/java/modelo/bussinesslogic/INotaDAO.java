package modelo.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Nota;

public interface INotaDAO {
    
    public ArrayList<Nota> consultarNotas(int usiario_id, Integer libreta_id, Integer prioridad_id) throws SQLException;
    public int registrarNota(Nota nota) throws SQLException;
    public int actualizarNota(Nota nota, String titulo) throws SQLException;
    public boolean eliminarNota(int nota_id) throws SQLException;
}
