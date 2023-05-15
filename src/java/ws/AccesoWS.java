package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.bussinesslogics.UsuarioDAO;
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
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(
            @FormParam("celular") String celular,
            @FormParam("contrasena") String contrasena) {
        Respuesta res = new Respuesta();
        //--------VALIDAR PARAMETROS DE ENTRADA--------------//
        if (celular == null || celular.trim().isEmpty()) {
            res.setError(true);
            res.setMensaje("El email es un dato requerido...");
            return res;
        } else if (contrasena == null || contrasena.trim().isEmpty()) {
            res.setError(true);
            res.setMensaje("La contraseña es un dato requerido...");
            return res;
        }
        //--------VALIDAR CREDENCIALES DEL EMPLEADO----------//
        Usuario e = UsuarioDAO.login(celular, contrasena);
        if (e == null) {
            res.setError(true);
            res.setMensaje("No se encontró ningún empleado con esas credenciales...");
            return res;
        }
        //------GENERAR TOKEN CON JWT Y DEVOLVERLO-----------//
        SesionToken s = new SesionToken();
        s.setId(e.getId());
        s.setNombre(e.getNombres());
        s.setEmail(e.getEmail());
        s = AutorizacionTokenJWT.generarToken(s);
        if (s == null || s.getTokenAcceso() == null || s.getTokenAcceso().isEmpty()) {
            res.setError(true);
            res.setMensaje("No se puede generar el token de acceso...");
        } else {
            res.setError(false);
            res.setMensaje("Bienvenido: " + s.getNombre());
            res.setSesionToken(s);
        }
        //---------------------------------------------------//
        return res;
    }
    
    @POST
    @Path("activar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta activar(
            @FormParam("celular") String celular,
            @FormParam("OTP") String contrasena) {
        Respuesta res = new Respuesta();
        
        return res;
    }
    
    @POST
    @Path("registro")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(
            @FormParam("celular") String celular,
            @FormParam("OTP") String contrasena) {
        Respuesta res = new Respuesta();
        
        return res;
    }
    
    

}
