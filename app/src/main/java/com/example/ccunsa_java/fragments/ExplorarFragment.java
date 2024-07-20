package com.example.ccunsa_java.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccunsa_java.R;
import com.example.ccunsa_java.adaptadores.AdaptadorResultados;
import com.example.ccunsa_java.adaptadores.OnResultadoClickListener;
import com.example.ccunsa_java.modelos.ResultadosViewModel;
import com.example.ccunsa_java.objetos.ResultadoFiltro;

public class ExplorarFragment extends Fragment implements OnResultadoClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerListaResultados;
    private ResultadosViewModel resultadosModel;
    private AdaptadorResultados adaptadorResultados;

    public ExplorarFragment() {
        // Required empty public constructor
    }

    public static ExplorarFragment newInstance(String param1, String param2) {
        ExplorarFragment fragment = new ExplorarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explorar, container, false);
        resultadosModel = new ViewModelProvider(requireActivity()).get(ResultadosViewModel.class);
        recyclerListaResultados = view.findViewById(R.id.recyclerFiltros);
        recyclerListaResultados.setLayoutManager(new GridLayoutManager(getContext(),3));

        adaptadorResultados = new AdaptadorResultados(resultadosModel.getResultadosLiveData().getValue(),getContext(),this);
        recyclerListaResultados.setAdapter(adaptadorResultados);
        Log.d("AdaptadorResultado", "Adaptador configurado y asignado al RecyclerView.");
        return view;
    }
    @Override
    public void onResultadoClick(ResultadoFiltro resultado) {
        resultadosModel.setResultadoSeleccionado(resultado);
        //Cargar fragment detalle
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, ExposicionFragment.class, null)
                .addToBackStack(null)
                .commit();
    }
}