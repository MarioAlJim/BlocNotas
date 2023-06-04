package modelo.bussinesslogic;

import dataaccess.DataBaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.pojos.Usuario;
import static java.sql.Types.INTEGER;


public class UsuarioDAO implements IUsuarioDAO{

    @Override
    public boolean registroUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL SPI_RegistrarUsuario(?,?,?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getNombres());
        callableStatement.setString(2, usuario.getApellidos());
        callableStatement.setString(3, usuario.getCelular());
        callableStatement.setString(4, usuario.getContrasena());
        callableStatement.setString(5, usuario.getOtp());
        callableStatement.registerOutParameter(6, INTEGER);
        callableStatement.execute();
        int affectedRows = 0;
        affectedRows = callableStatement.getInt(6);
        dbc.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean activarUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL SPU_ActivarUsuario(?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getCelular());
        callableStatement.setString(2, usuario.getOtp());
        callableStatement.registerOutParameter(3, INTEGER);
        callableStatement.executeUpdate();
        int affectedRows = callableStatement.getInt(3);
        dbc.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public Usuario login(Usuario usuario) throws SQLException {
        String query = "{CALL SPS_Login(?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getCelular());
        callableStatement.setString(2, usuario.getContrasena());
        ResultSet resultSet = callableStatement.executeQuery();
        Usuario usuarioRecuperado = new Usuario();
        if(resultSet.next()) {
            usuarioRecuperado.setUsuario_id(resultSet.getInt("usuario_id"));
            usuarioRecuperado.setNombres(resultSet.getString("nombres"));
            usuarioRecuperado.setApellidos(resultSet.getString("apellidos"));
            usuarioRecuperado.setTiempo_registro(resultSet.getTimestamp("tiempo_registro"));
            usuarioRecuperado.setActivo(resultSet.getInt("activo"));
            usuarioRecuperado.setCelular(resultSet.getString("celular"));
            usuarioRecuperado.setContrasena(resultSet.getString("contrasena"));
            usuarioRecuperado.setUltimo_token_acceso(resultSet.getString("ultimo_token_acceso"));
            usuarioRecuperado.setTiempo_ultimo_acceso(resultSet.getTimestamp("tiempo_ultimo_acceso"));
            usuarioRecuperado.setOtp(resultSet.getString("otp"));
            usuarioRecuperado.setTiempo_activacion(resultSet.getTimestamp("tiempo_activacion"));
        }
        dbc.closeConection();
        return usuarioRecuperado;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL SPU_ActualizarUsuario(?,?,?,?,?,?)}";
        DataBaseConnection dbc = new DataBaseConnection(); 
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, usuario.getUsuario_id());
        callableStatement.setString(2, usuario.getNombres());
        callableStatement.setString(3, usuario.getApellidos());
        callableStatement.setString(4, usuario.getCelular());
        callableStatement.setString(5, usuario.getContrasena());
        callableStatement.registerOutParameter(6, INTEGER);
        callableStatement.execute();
        int affectedRows = callableStatement.getInt(6);
        dbc.closeConection();
        return (affectedRows != 0);
    }
}

