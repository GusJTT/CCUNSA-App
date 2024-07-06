package com.example.ccunsa_java.objetos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exposicion {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "url_imagen")
    private String urlImagen;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "descripcion")
    private String descripcion;
}
