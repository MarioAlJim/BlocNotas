package modelo.bussinesslogic;

import dataaccess.DataBaseConnection;
import modelo.pojos.Libreta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.sql.Types.INTEGER;


public class LibretaDAO implements ILibretaDAO {
    
    @Override
    public ArrayList<Libreta> consultarLibretasDeUsuario(int usuario_id) throws SQLException {
        ArrayList<Libreta> libretas = new ArrayList<>();
        String query = "{CALL SPS_ObtenerLibreta(?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, usuario_id);
        ResultSet resultSet = callableStatement.executeQuery();
        while(resultSet.next()) {
            Libreta libretaObtenida = new Libreta();
            libretaObtenida.setLibreta_id(resultSet.getInt("libreta_id"));
            libretaObtenida.setNombre(resultSet.getString("nombre"));
            libretaObtenida.setColor(resultSet.getString("color_hexadecimal"));
            libretaObtenida.setUsuario_id(usuario_id);
            libretas.add(libretaObtenida);
        }
        dbc.closeConection();
        return libretas;
    }

    @Override
    public boolean registrarLibreta(Libreta libreta) throws SQLException {
        String query = "{CALL SPI_RegistrarLibreta(?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, libreta.getNombre());
        callableStatement.setString(2, libreta.getColor());
        callableStatement.setInt(3, libreta.getUsuario_id());
        callableStatement.registerOutParameter(4, INTEGER);
        callableStatement.execute();
        int result = 0;
        result = callableStatement.getInt(4);
        dbc.closeConection();
        return (result != 0);
    }

    @Override
    public int actualizarLibreta(Libreta libreta) throws SQLException {
        String query = "{CALL SPU_ActualizarLibreta(?,?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, libreta.getLibreta_id());
        callableStatement.setString(2, libreta.getNombre());
        callableStatement.setString(3, libreta.getColor());
        callableStatement.setInt(4, libreta.getUsuario_id());
        callableStatement.registerOutParameter(5, INTEGER);
        callableStatement.execute();
        int affectedRows = callableStatement.getInt(5);
        dbc.closeConection();
        return affectedRows;
    }

    @Override
    public int eliminarLibreta(int libreta_id) throws SQLException {
        String query = "{CALL SPD_EliminarLibreta(?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, libreta_id);
        callableStatement.registerOutParameter(2, INTEGER);
        callableStatement.execute();
        int affectedRows = callableStatement.getInt(2);
                dbc.closeConection();
        return affectedRows;
    }
}
