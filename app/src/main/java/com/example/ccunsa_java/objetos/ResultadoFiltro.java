package com.example.ccunsa_java.objetos;

public class ResultadoFiltro {
    private int id;
    private String urlImagen;
    private String nombre;

    public ResultadoFiltro(int id, String urlImagen, String nombre) {
        this.urlImagen = urlImagen;
        this.nombre = nombre;
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
