package modelo.pojos;
import java.util.List;

public class Respuesta {

    private boolean error;
    private String mensaje;
    private Usuario usuario;
    private List<Usuario> lista;
    private SesionToken sesionToken;

    public Respuesta() {
    }

    public Respuesta(boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setEmpleado(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public SesionToken getSesionToken() {
        return sesionToken;
    }

    public void setSesionToken(SesionToken sesionToken) {
        this.sesionToken = sesionToken;
    }
    
    
}
