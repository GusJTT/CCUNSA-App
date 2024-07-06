package com.example.ccunsa_java.modelos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ccunsa_java.objetos.ObraDeArte;

import java.util.ArrayList;
import java.util.List;

public class CuadrosViewModel extends ViewModel {
    private MutableLiveData<List<ObraDeArte>> cuadrosLiveData;
    private MutableLiveData<ObraDeArte> cuadroSeleccionado;
    private List<ObraDeArte> listaObrasDeArte;

    public CuadrosViewModel() {
        cuadrosLiveData = new MutableLiveData<>();
        cuadroSeleccionado = new MutableLiveData<>();
        loadCuadros();
    }
    public LiveData<List<ObraDeArte>> getCuadrosLiveData() {
        return cuadrosLiveData;
    }

    public LiveData<ObraDeArte> getCuadroSeleccionado() {
        return cuadroSeleccionado;
    }

    public void setCuadroSeleccionado(ObraDeArte obraDeArte) {
        cuadroSeleccionado.setValue(obraDeArte);
    }

    public void setCuadroSeleccionadoPorId(int id) {
        ObraDeArte obraDeArte = getCuadroPorId(id);
        cuadroSeleccionado.setValue(obraDeArte);
    }
    private ObraDeArte getCuadroPorId(int id) {
        if (listaObrasDeArte != null) {
            for (ObraDeArte obraDeArte : listaObrasDeArte) {
                if (obraDeArte.getId() == id) {
                    return obraDeArte;
                }
            }
        }
        return null;
    }
    private void loadCuadros() {
        listaObrasDeArte = new ArrayList<>();

        listaObrasDeArte.add(new ObraDeArte(
                1,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/89.jpg",
                "LA YAKANA EN ANTAKARI",
                1,
                1,
                1,
                "2024-05-12",
                "Fotografia",
                "Pintura de luz, larga exposición, apilado",
                "DIMENSIONES: 50 x 30 cm.\n"
        ));
        listaObrasDeArte.add(new ObraDeArte(
                2,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/85.jpg",
                "SOGAY",
                1,
                1,
                1,
                "2024-05-12",
                "Fotografia",
                "Larga exposiciòn",
                "DIMENSIONES:  30 x 45 cm.\n"
        ));
        listaObrasDeArte.add(new ObraDeArte(
                2,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/85.jpg",
                "POCSI",
                1,
                1,
                1,
                "2024-05-14",
                "Fotografia",
                "Pintura de luz, y larga exposición",
                "DIMENSIONES: 30 x 45 cm.\n"
        ));

        Log.d("SharedViewModel", "Lista de cuadros inicializada con " + listaObrasDeArte.size() + " elementos.");

        cuadrosLiveData.setValue(listaObrasDeArte);
    }
}

