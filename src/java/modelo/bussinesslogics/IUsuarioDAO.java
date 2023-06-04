/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bussinesslogics;

import java.sql.SQLException;
import modelo.pojos.Usuario;

public interface IUsuarioDAO {
    public boolean registroUsuario(Usuario usuario) throws SQLException;
    public boolean activarUsuario(Usuario usuario) throws SQLException;
    public Usuario login(Usuario usuario) throws SQLException;
    public boolean actualizarUsuario(Usuario usuario) throws SQLException;
}

