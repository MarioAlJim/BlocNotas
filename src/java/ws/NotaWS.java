package ws;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.bussinesslogic.NotaDAO;
import modelo.pojos.Nota;
import modelo.pojos.Respuesta;

@Path("auth/nota")
public class NotaWS {

    @Context
    private UriInfo context;

    public NotaWS() {
    }
    
    @GET
    @Path("consultar/{usuario_id}/{libreta_id}/{prioridad}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultarNotas(
            @PathParam("usuario_id") Integer usuario_id,
            @PathParam("libreta_id") Integer libreta_id,
            @PathParam("prioridad") Integer prioridad_id) {
        Respuesta respuesta = new Respuesta();

        if (usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un usuario valido valido");
        } else {
            NotaDAO notaDAO = new NotaDAO();
            ArrayList<Nota> notasRecuperadas = new ArrayList<>();
            try {
                notasRecuperadas = notaDAO.consultarNotas(usuario_id, libreta_id, prioridad_id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (notasRecuperadas.isEmpty()) {
                respuesta.setError(true);
                respuesta.setMensaje("El usuario no tiene notas");
            } else {
                respuesta.setNotas(notasRecuperadas);
                respuesta.setError(false);
                respuesta.setMensaje("Libretas recuperadas");
            }
        }
        return respuesta;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta crearNota(
            @FormParam("titulo") String titulo,
            @FormParam("contenido") String contenido,
            @FormParam("usuario_id") Integer usuario_id,
            @FormParam("libreta_id") Integer libreta_id,
            @FormParam("prioridad_id") Integer prioridad_id) {
        Respuesta respuesta = new Respuesta();

        if (titulo == null || titulo.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un titulo");
        } else if (contenido == null || contenido.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar contenido en la nota");
        } else if (usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else if (libreta_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if (prioridad_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de prioridad valido");
        } else {
            Nota nota = new Nota();
            nota.setTitulo(titulo);
            nota.setContenido(contenido);
            nota.setUsuario_id(usuario_id);
            nota.setLibreta_id(libreta_id);
            nota.setPrioridad_id(prioridad_id);
            NotaDAO notaDAO = new NotaDAO();
            int notaRegistrada = 0;
            try {
                notaRegistrada = notaDAO.registrarNota(nota);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (notaRegistrada == 1) {
                respuesta.setError(false);
                respuesta.setMensaje("Registro exitoso");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro de notafallido");
            }
        }
        return respuesta;
    }
    
    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
            @FormParam("titulo") String titulo,
            @FormParam("titulo_anterior") String titulo_anterior,
            @FormParam("contenido") String contenido,
            @FormParam("usuario_id") Integer usuario_id,
            @FormParam("libreta_id") Integer libreta_id,
            @FormParam("prioridad_id") Integer prioridad_id) {
        Respuesta respuesta = new Respuesta();

        if (titulo == null || titulo.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un titulo");
        } else if (contenido == null || contenido.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar contenido en la nota");
        } else if (usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else if (libreta_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if (prioridad_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de prioridad valido");
        } else {
            Nota nota = new Nota();
            nota.setTitulo(titulo);
            nota.setContenido(contenido);
            nota.setUsuario_id(usuario_id);
            nota.setLibreta_id(libreta_id);
            nota.setPrioridad_id(prioridad_id);
            NotaDAO notaDAO = new NotaDAO();
            int notaActualziada = 0;
            try {
                notaActualziada = notaDAO.actualizarNota(nota, titulo_anterior);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            switch (notaActualziada) {
                case 1:
                    respuesta.setError(false);
                    respuesta.setMensaje("Actualizacion exitosa");
                    break;
                default:
                    respuesta.setError(true);
                    respuesta.setMensaje("Actualizacion fallida, nota no encontrada");
                    break;
            }
        }
        return respuesta;
    }

    @DELETE
    @Path("eliminar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(
            @FormParam("nota_id") Integer nota_id) {
        Respuesta respuesta = new Respuesta();

        if (nota_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de nota valido");
        } else {
            NotaDAO notaDAO = new NotaDAO();
            boolean notaEliminada = false;
            try {
                notaEliminada = notaDAO.eliminarNota(nota_id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (notaEliminada) {
                respuesta.setError(false);
                respuesta.setMensaje("Eliminacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Eliminacion fallida");
            }
        }

        return respuesta;
    }
}
