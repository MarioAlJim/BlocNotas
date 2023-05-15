package modelo.pojos;

public class Prioridad {

    private int prioridad_id;
    private String nombre;

    public Prioridad() {
    }

    public Prioridad(int prioridad_id, String nombre) {
        this.prioridad_id = prioridad_id;
        this.nombre = nombre;
    }

    public int getPrioridad_id() {
        return prioridad_id;
    }

    public void setPrioridad_id(int prioridad_id) {
        this.prioridad_id = prioridad_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
