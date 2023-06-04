package modelo.pojos;

public class SesionToken {

    private Integer usuario_id;
    private String nombres;
    private String celular;
    private String tokenacceso;

    public SesionToken() {
    }

    public SesionToken(Integer idUsuario, String nombres, String apellidos, String celular, String tokenacceso) {
        this.usuario_id = idUsuario;
        this.nombres = nombres;
        this.celular = celular;
        this.tokenacceso = tokenacceso;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.usuario_id = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTokenacceso() {
        return tokenacceso;
    }

    public void setTokenacceso(String tokenacceso) {
        this.tokenacceso = tokenacceso;
    }
}
