package com.example.ccunsa_java.objetos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Autor {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "url_imagen")
    private String urlImagen;

    @ColumnInfo(name = "nombres")
    private String nombres;

    @ColumnInfo(name = "apellidos")
    private String apellidos;
}
