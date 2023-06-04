package ws;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import modelo.bussinesslogic.Activacion;
import modelo.bussinesslogic.UsuarioDAO;
import modelo.pojos.Respuesta;
import modelo.pojos.SesionToken;
import modelo.pojos.Usuario;
import seguridad.AutorizacionTokenJWT;

@Path("bsc/acceso")
public class AccesoWS {

    @Context
    private UriInfo context;

    public AccesoWS() {
    }
    
    @POST
    @Path("registro")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registro(
            @FormParam("nombre") String nombre,
            @FormParam("apellidos") String apellidos,
            @FormParam("celular") String celular,
            @FormParam("contrasena") String contrasena) {
        Respuesta respuesta = new Respuesta();

        if(nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro nombre es obligatorio");
        } else if(apellidos == null || apellidos.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro apellidos es obligatorio");
        } else if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro celular es obligatorio");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro contrasena es obligatorio");
        } else {
            Usuario usuario = new Usuario();
            Activacion activacion = new Activacion();
            usuario.setNombres(nombre);
            usuario.setApellidos(apellidos);
            usuario.setCelular(celular);
            usuario.setContrasena(contrasena);
            usuario.setOtp(activacion.generarOTP());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean usuarioRegistrado = false;
            try {
                usuarioRegistrado = usuarioDAO.registroUsuario(usuario);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(usuarioRegistrado) {
                respuesta.setError(false);
                respuesta.setMensaje("Registro exitoso");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro fallido");
            }
        }
        return respuesta;
    }
    
    
    @POST
    @Path("activar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta activar(
            @FormParam("celular") String celular,
            @FormParam("otp") String otp) {
        Respuesta respuesta = new Respuesta();
        
        if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro celular es obligatorio");
        } else if(otp == null || otp.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El codigo de activacion es obligatorio");
        } else {
            Usuario usuario = new Usuario();
            usuario.setCelular(celular);
            usuario.setOtp(otp);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean activado = false;
            try {
                activado = usuarioDAO.activarUsuario(usuario);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(activado) {
                respuesta.setError(false);
                respuesta.setMensaje("Activacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Activacion fallida");
            }
        }
        
        return respuesta;
    }
    
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(
            @FormParam("celular") String celular,
            @FormParam("contrasena") String contrasena) {
        Respuesta respuesta = new Respuesta();
        
        if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro celular es obligatorio");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El parametro contrasena es obligatorio");
        } else {
            Usuario usuario = new Usuario();
            usuario.setCelular(celular);
            usuario.setContrasena(contrasena);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuarioRecuperado = new Usuario();
            try { 
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
       System.out.println("Driver funciona correctamente."); 
    } catch (ClassNotFoundException e) {
       System.out.println("Error: " + e.getMessage()); 
}
            try {
                usuarioRecuperado = usuarioDAO.login(usuario);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(usuarioRecuperado.getUsuario_id()== 0) {
                respuesta.setError(true);
                respuesta.setMensaje("Credenciales invalidas");
            } else {
                SesionToken sesionToken = new SesionToken();
                sesionToken.setIdUsuario(usuarioRecuperado.getUsuario_id());
                sesionToken.setNombres(usuarioRecuperado.getNombres());
                sesionToken.setCelular(usuarioRecuperado.getCelular());
                sesionToken = AutorizacionTokenJWT.generarToken(sesionToken);
                if(sesionToken == null || sesionToken.getTokenacceso()== null || sesionToken.getTokenacceso().isEmpty()) {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se puede generar el token de acceso...");
                } else {
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido: " + sesionToken.getNombres());
                    respuesta.setUsuario(usuarioRecuperado);
                    respuesta.setSesionToken(sesionToken);
                }
            }
        }
        return respuesta;
    }
}
