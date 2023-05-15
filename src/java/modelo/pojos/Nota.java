package modelo.pojos;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class Nota {
      private int nota_id;
      private String titulo;
      private String contenido;
      private Timestamp tiempo_creacion;
      private int eliminado;
      private int usuario_id;
      private int libreta_id;
      private int propiedad_id;

    public Nota(int nota_id, String titulo, String contenido, Timestamp tiempo_creacion, int eliminado, int usuario_id, int libreta_id, int propiedad_id) {
        this.nota_id = nota_id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tiempo_creacion = tiempo_creacion;
        this.eliminado = eliminado;
        this.usuario_id = usuario_id;
        this.libreta_id = libreta_id;
        this.propiedad_id = propiedad_id;
    }

    public Nota() {
    }

    public int getNota_id() {
        return nota_id;
    }

    public void setNota_id(int nota_id) {
        this.nota_id = nota_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getTiempo_creacion() {
        return tiempo_creacion;
    }

    public void setTiempo_creacion(Timestamp tiempo_creacion) {
        this.tiempo_creacion = tiempo_creacion;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getLibreta_id() {
        return libreta_id;
    }

    public void setLibreta_id(int libreta_id) {
        this.libreta_id = libreta_id;
    }

    public int getPropiedad_id() {
        return propiedad_id;
    }

    public void setPropiedad_id(int propiedad_id) {
        this.propiedad_id = propiedad_id;
    }
}
