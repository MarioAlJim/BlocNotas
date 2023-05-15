package modelo.pojos;

public class SesionToken {
    private Integer id;
    private String nombre;
    private String email;
    private String tokenAcceso;

    public SesionToken() {
        
    } 
    
    public SesionToken(Integer id, String nombre, String email, String tokenacceso) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tokenAcceso = tokenacceso;
    }
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenacceso) {
        this.tokenAcceso = tokenacceso;
    }
    
    
}
