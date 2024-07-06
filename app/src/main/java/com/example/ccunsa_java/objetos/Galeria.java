package com.example.ccunsa_java.objetos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Galeria {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "id_exposicion")
    private int idExposicion;
}
