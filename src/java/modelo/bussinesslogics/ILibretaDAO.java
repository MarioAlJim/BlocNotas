package modelo.bussinesslogics;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pojos.Libreta;

public interface ILibretaDAO {
    public ArrayList<Libreta> consultarLibretasDeUsuario(int usuario_id) throws SQLException;
    public boolean registrarLibreta(Libreta libreta) throws SQLException;
    public int actualizarLibreta(Libreta libreta) throws SQLException;
    public int eliminarLibreta(int libreta_id) throws SQLException;
}

