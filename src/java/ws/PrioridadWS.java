package ws;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import modelo.bussinesslogic.PrioridadDAO;
import modelo.pojos.Prioridad;
import modelo.pojos.Respuesta;

@Path("auth/prioridad")
public class PrioridadWS {

    @Context
    private UriInfo context;

    public PrioridadWS() {
    }
    
    @GET
    @Path("consultar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta obtenerPrioridades() {
        Respuesta respuesta = new Respuesta();
        PrioridadDAO prioridadDAO = new PrioridadDAO();
        ArrayList<Prioridad> prioridades = new ArrayList<>();
        try {
            prioridades = prioridadDAO.consultarPrioridades();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (prioridades.isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Prioridades no encontradas");
        } else {
            respuesta.setPrioridades(prioridades);
            respuesta.setError(false);
            respuesta.setMensaje("Prioridades recuperadas");
        }
        return respuesta;
    }
}
