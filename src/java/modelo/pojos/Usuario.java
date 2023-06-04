package modelo.pojos;

import java.sql.Timestamp;

public class Usuario {
    private int usuario_id;
    private String nombres;
    private String apellidos;
    private Timestamp tiempo_registro;
    private int activo;
    private String celular;
    private String contrasena;
    private String ultimo_token_acceso;
    private Timestamp tiempo_ultimo_acceso;
    private String otp;
    private Timestamp tiempo_activacion;

    public Usuario() {
        usuario_id = 0;
    }

    public Usuario(int usuario_id, String nombres, String apellidos, Timestamp tiempo_registro, int activo, String celular, String contrasena, String ultimo_token_acceso, Timestamp tiempo_ultimo_acceso, String otp, Timestamp tiempo_activacion) {
        this.usuario_id = usuario_id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tiempo_registro = tiempo_registro;
        this.activo = activo;
        this.celular = celular;
        this.contrasena = contrasena;
        this.ultimo_token_acceso = ultimo_token_acceso;
        this.tiempo_ultimo_acceso = tiempo_ultimo_acceso;
        this.otp = otp;
        this.tiempo_activacion = tiempo_activacion;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Timestamp getTiempo_registro() {
        return tiempo_registro;
    }

    public void setTiempo_registro(Timestamp tiempo_registro) {
        this.tiempo_registro = tiempo_registro;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUltimo_token_acceso() {
        return ultimo_token_acceso;
    }

    public void setUltimo_token_acceso(String ultimo_token_acceso) {
        this.ultimo_token_acceso = ultimo_token_acceso;
    }

    public Timestamp getTiempo_ultimo_acceso() {
        return tiempo_ultimo_acceso;
    }

    public void setTiempo_ultimo_acceso(Timestamp tiempo_ultimo_acceso) {
        this.tiempo_ultimo_acceso = tiempo_ultimo_acceso;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getTiempo_activacion() {
        return tiempo_activacion;
    }

    public void setTiempo_activacion(Timestamp tiempo_activacion) {
        this.tiempo_activacion = tiempo_activacion;
    }

    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

