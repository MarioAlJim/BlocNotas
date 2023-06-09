package modelo.bussinesslogics;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dataaccesss.DataBaseConnection;
import modelo.pojos.Prioridad;

public class PrioridadDAO implements IPrioridadDAO{

    @Override
    public ArrayList<Prioridad> consultarPrioridades() throws SQLException {
        ArrayList<Prioridad> prioridades = new ArrayList<>();
        String query = "{CALL SPS_ObtenerPrioridades()}";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        ResultSet resultSet = callableStatement.executeQuery();
        while(resultSet.next()) {
            Prioridad prioridad = new Prioridad();
            prioridad.setPrioridad_id(resultSet.getInt("prioridad_id"));
            prioridad.setNombre(resultSet.getString("nombre"));
            prioridades.add(prioridad);
        }
        dbc.closeConection();
        return prioridades;
    }
}
