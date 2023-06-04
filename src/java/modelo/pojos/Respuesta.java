package modelo.pojos;
import java.util.ArrayList;

public class Respuesta {
    private boolean error;
    private String mensaje;
    private SesionToken sesionToken;
    private Usuario usuario;
    private ArrayList<Libreta> libretas;
    private ArrayList<Prioridad> prioridades;
    private ArrayList<Nota> notas;

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

    public SesionToken getSesionToken() {
        return sesionToken;
    }

    public void setSesionToken(SesionToken sesionToken) {
        this.sesionToken = sesionToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Libreta> getLibretas() {
        return libretas;
    }

    public void setLibretas(ArrayList<Libreta> libretas) {
        this.libretas = libretas;
    }

    public ArrayList<Prioridad> getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(ArrayList<Prioridad> prioridades) {
        this.prioridades = prioridades;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }
}