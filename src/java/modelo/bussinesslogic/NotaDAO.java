/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bussinesslogic;

import dataaccess.DataBaseConnection;
import modelo.pojos.Nota;
import java.sql.*;
import java.util.ArrayList;

public class NotaDAO implements INotaDAO {
    @Override
    public ArrayList<Nota> consultarNotas(int usuario_id, Integer libreta_id, Integer prioridad_id) throws SQLException {
        ArrayList<Nota> notas = new ArrayList<>();
        String query = "{CALL SPS_ObtenerNotas(?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        PreparedStatement callableStatement = connection.prepareStatement(query);
        callableStatement.setInt(1, usuario_id);
        if(libreta_id == null) {
            callableStatement.setNull(2, libreta_id);
        } else {
            callableStatement.setInt(2, libreta_id);
        }
        if(prioridad_id == null) {
            callableStatement.setNull(3, prioridad_id);
        } else {
            callableStatement.setInt(3, prioridad_id);
        }
        
        
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            Nota notaRecuperada = new Nota();
            notaRecuperada.setNota_id(resultSet.getInt(1));
            notaRecuperada.setTitulo(resultSet.getString(2));
            notaRecuperada.setContenido(resultSet.getString(3));
            notaRecuperada.setTiempo_creacion(resultSet.getTimestamp(4));
            notaRecuperada.setUsuario_id(usuario_id);
            notaRecuperada.setLibreta_id(resultSet.getInt(6));
            notaRecuperada.setPrioridad_id(resultSet.getInt(7));
            notas.add(notaRecuperada);
        }
        dbc.closeConection();
        return notas;
    }

    /*        ArrayList<Nota> notas = new ArrayList<>();
        String query = "SELECT * FROM nota " +
                "WHERE usuario_creacion_id = ? " +
                "AND libreta_id = ISNULL(?, libreta_id) "; /* + 
                "AND prioridad_id = ISNULL(?, prioridad_id) " + 
                "AND eliminado = 0";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        PreparedStatement callableStatement = connection.prepareStatement(query);
        callableStatement.setInt(1, usuario_id);
           callableStatement.setNull(2, libreta_id);
           // callableStatement.setInt(3, prioridad_id);*/
        
        
    @Override
    public int registrarNota(Nota nota) throws SQLException {
        String query = "{CALL SPI_RegistrarNota(?,?,?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, nota.getTitulo());
        callableStatement.setString(2, nota.getContenido());
        callableStatement.setInt(3, nota.getUsuario_id());
        callableStatement.setInt(4, nota.getLibreta_id());
        callableStatement.setInt(5, nota.getPrioridad_id());
        callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
        callableStatement.execute();
        int resultado = 0;
        resultado = callableStatement.getInt(6);
        dbc.closeConection();
        return resultado;
    }

    @Override
    public int actualizarNota(Nota nota, String titulo) throws SQLException {
        String query = "{CALL SPU_ActualizarNota(?,?,?,?,?,?,?)}";
        int resultado = 0;
        if (nota.getTitulo() == titulo || tituloDisponible(nota.getTitulo(), nota.getLibreta_id(), nota.getUsuario_id())) {
            DataBaseConnection dbc = new DataBaseConnection();
            Connection connection = dbc.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, nota.getNota_id());
            callableStatement.setString(2, nota.getTitulo());
            callableStatement.setString(3, nota.getContenido());
            callableStatement.setInt(4, nota.getUsuario_id());
            callableStatement.setInt(5, nota.getLibreta_id());
            callableStatement.setInt(6, nota.getPrioridad_id());
            callableStatement.registerOutParameter(7, java.sql.Types.INTEGER);
            callableStatement.execute();

            resultado = callableStatement.getInt(7);
            dbc.closeConection();
        }
        return resultado;
    }

    private boolean tituloDisponible(String titulo, int libreta_id, int usuario_id) throws SQLException {
        String query = "{CALL SPS_ComprobarDisponibilidad(?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, titulo);
        callableStatement.setInt(2, libreta_id);
        callableStatement.setInt(3, usuario_id);
        callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
        callableStatement.execute();
        return (callableStatement.getInt(4) == 0);
    }

    @Override
    public boolean eliminarNota(int nota_id) throws SQLException {
        String query = "{CALL SPD_EliminarNota(?,?)}";
        DataBaseConnection dbc = new DataBaseConnection();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, nota_id);
        callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
        callableStatement.executeUpdate();
        int resultado = 0;
        resultado = callableStatement.getInt(2);
        dbc.closeConection();
        return (resultado != 2);
    }
}
