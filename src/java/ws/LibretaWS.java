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
import modelo.bussinesslogic.LibretaDAO;
import modelo.pojos.Libreta;
import modelo.pojos.Respuesta;

@Path("auth/libreta")
public class LibretaWS {

    @Context
    private UriInfo context;

    public LibretaWS() {
    }

    @GET
    @Path("consultar/{usuario_id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultar(
            @PathParam("usuario_id") Integer uduario_id) {
        Respuesta respuesta = new Respuesta();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver funciona correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        if (uduario_id == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un usuario valido valido");
        } else {
            LibretaDAO libretaDAO = new LibretaDAO();
            ArrayList<Libreta> libretasRecuperadas = new ArrayList<>();
            try {
                libretasRecuperadas = libretaDAO.consultarLibretasDeUsuario(uduario_id);
                System.out.print(libretasRecuperadas.get(0).getLibreta_id());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (libretasRecuperadas.isEmpty()) {
                respuesta.setError(true);
                respuesta.setMensaje("El usuario no tiene libretas");
            } else {
                respuesta.setLibretas(libretasRecuperadas);
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
    public Respuesta registrar(
            @FormParam("nombre") String nombre,
            @FormParam("colorHexadecimal") String color_hexadecimal,
            @FormParam("usuario_id") Integer usuario_id) {
        Respuesta respuesta = new Respuesta();

        if (nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if (color_hexadecimal == null || color_hexadecimal.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un color hexadecimal");
        } else if (usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setNombre(nombre);
            libreta.setColor(color_hexadecimal);
            libreta.setUsuario_id(usuario_id);
            LibretaDAO libretaDAO = new LibretaDAO();
            boolean libretaRegistrada = false;
            try {
                libretaRegistrada = libretaDAO.registrarLibreta(libreta);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (libretaRegistrada) {
                respuesta.setError(false);
                respuesta.setMensaje("Registro exitoso");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro fallido, una libreta tiene el mismo nombre");
            }
        }
        return respuesta;
    }

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
            @FormParam("libreta_id") Integer libreta_id,
            @FormParam("nombre") String nombre,
            @FormParam("colorHexadecimal") String color_hexadecimal,
            @FormParam("usuario_id") Integer usuario_id) {
        Respuesta respuesta = new Respuesta();

        if (libreta_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if (nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if (color_hexadecimal == null || color_hexadecimal.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un color hexadecimal");
        } else if (usuario_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setLibreta_id(libreta_id);
            libreta.setNombre(nombre);
            libreta.setColor(color_hexadecimal);
            libreta.setUsuario_id(usuario_id);
            LibretaDAO libretaDAO = new LibretaDAO();
            int libretaActualizada = 0;
            try {
                libretaActualizada = libretaDAO.actualizarLibreta(libreta);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            switch (libretaActualizada) {
                case 1:
                    respuesta.setError(false);
                    respuesta.setMensaje("Actualizacion exitosa");
                    break;
                case 2:
                    respuesta.setError(true);
                    respuesta.setMensaje("Actualizacion fallida, una libreta tiene el mismo nombre");
                    break;
                default:
                    respuesta.setError(true);
                    respuesta.setMensaje("Actualizacion fallida, libreta no encontrada");
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
            @FormParam("libreta_id") Integer libreta_id) {
        Respuesta respuesta = new Respuesta();

        if (libreta_id == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else {
            LibretaDAO libretaDAO = new LibretaDAO();
            int libretaEliminada = -1;
            try {
                libretaEliminada = libretaDAO.eliminarLibreta(libreta_id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (libretaEliminada == 1) {
                respuesta.setError(false);
                respuesta.setMensaje("Eliminacion exitosa");
            } else if (libretaEliminada == 2) {
                respuesta.setError(true);
                respuesta.setMensaje("Eliminacion fallida, la libreta tiene notas");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Eliminacion fallida");
            }
        }

        return respuesta;
    }
}
