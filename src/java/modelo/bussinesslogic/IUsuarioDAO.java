package modelo.bussinesslogic;

import java.sql.SQLException;
import modelo.pojos.Usuario;

public interface IUsuarioDAO {
    public boolean registroUsuario(Usuario usuario) throws SQLException;
    public boolean activarUsuario(Usuario usuario) throws SQLException;
    public Usuario login(Usuario usuario) throws SQLException;
    public boolean actualizarUsuario(Usuario usuario) throws SQLException;
}
