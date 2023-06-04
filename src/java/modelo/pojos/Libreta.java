package modelo.pojos;

public class Libreta {
    private int libreta_id;
    private String nombre;
    private String color;
    private int usuario_id;

    public Libreta() {
    }

    public Libreta(int libreta_id, String nombre, String color, int usuario_id) {
        this.libreta_id = libreta_id;
        this.nombre = nombre;
        this.color = color;
        this.usuario_id = usuario_id;
    }

    public int getLibreta_id() {
        return libreta_id;
    }

    public void setLibreta_id(int libreta_id) {
        this.libreta_id = libreta_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}