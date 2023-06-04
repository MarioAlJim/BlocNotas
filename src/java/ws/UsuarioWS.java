package ws;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.bussinesslogic.UsuarioDAO;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;


@Path("auth/usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;

    public UsuarioWS() {
    }

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
            @FormParam("usuario_id") Integer usuario_id,
            @FormParam("celular") String celular,
            @FormParam("nombre") String nombres,
            @FormParam("apellidos") String apellidos,
            @FormParam("contrasena") String contrasena) throws SQLException {
        Respuesta respuesta = new Respuesta();
        
        if(usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere un id de usuario valido");
        } else if(celular.trim().isEmpty() || celular == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id valido");
        } else if(nombres == null || nombres.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if(apellidos == null || apellidos.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar los apellidos");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar una contrasena");
        } else {
            Usuario usuario = new Usuario();
            usuario.setUsuario_id(usuario_id);
            usuario.setCelular(celular);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setContrasena(contrasena);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean usuarioActualizado = false;
            
            usuarioActualizado = usuarioDAO.actualizarUsuario(usuario);
            
            if(usuarioActualizado) {
                respuesta.setError(false);
                respuesta.setMensaje("Actualizacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Actualizacion fallida");
            }
        }
        
        return respuesta;
    }
}
