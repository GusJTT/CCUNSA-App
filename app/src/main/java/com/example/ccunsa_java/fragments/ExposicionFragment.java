package com.example.ccunsa_java.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ccunsa_java.R;
import com.example.ccunsa_java.adaptadores.AdaptadorObras;
import com.example.ccunsa_java.adaptadores.OnObraClickListener;
import com.example.ccunsa_java.modelos.ObrasViewModel;
import com.example.ccunsa_java.modelos.ResultadosViewModel;
import com.example.ccunsa_java.objetos.Exposicion;
import com.example.ccunsa_java.objetos.ObraDeArte;
import com.example.ccunsa_java.objetos.ResultadoFiltro;

public class ExposicionFragment extends Fragment implements OnObraClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerListaObras;
    private AdaptadorObras adaptadorObras;
    private ObrasViewModel obrasModel;
    private ResultadosViewModel resultadosModel;
    private ResultadoFiltro resultadoSeleccionado;

    private String mParam1;
    private String mParam2;

    public ExposicionFragment() {
        // Required empty public constructor
    }

    public static ExposicionFragment newInstance(String param1, String param2) {
        ExposicionFragment fragment = new ExposicionFragment();
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
        View view = inflater.inflate(R.layout.fragment_exposicion, container, false);
        TextView txtTituloExposicion = view.findViewById(R.id.txtTituloExposicion);
        TextView txtFechaExposicion = view.findViewById(R.id.txtFechaExposicion);
        TextView txtDescripcionExpocision = view.findViewById(R.id.txtDescripcionExpocision);
        ImageView imgFotoExposicion = view.findViewById(R.id.imgFotoExposicion);

        resultadosModel = new ViewModelProvider(requireActivity()).get(ResultadosViewModel.class);
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        // Observar el resultado seleccionado y actualizar la UI
        resultadosModel.getResultadoSeleccionado().observe(getViewLifecycleOwner(), resultado -> {
            Exposicion expoObtenida = consultaExposicion(resultadosModel.getResultadoSeleccionado().getValue());
            if (resultado != null) {
                txtTituloExposicion.setText(expoObtenida.getNombre());
                Glide.with(getContext())
                        .load(expoObtenida.getUrlImagen())
                        .centerCrop()
                        .into(imgFotoExposicion);
                txtFechaExposicion.setText(expoObtenida.getFecha());
                txtDescripcionExpocision.setText(expoObtenida.getDescripcion());
            }
        });


        resultadoSeleccionado = resultadosModel.getResultadoSeleccionado().getValue();
        recyclerListaObras = view.findViewById(R.id.recyclerFiltros);
        recyclerListaObras.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorObras = new AdaptadorObras(obrasModel.getObrasLiveData().getValue(),getContext(),this);
        recyclerListaObras.setAdapter(adaptadorObras);
        Log.d("AdaptadorCuadro", "Adaptador configurado y asignado al RecyclerView.");
        return view;
    }
    @Override
    public void onObraClick(ObraDeArte obra) {
        obrasModel.setObraSeleccionada(obra);
        //Cargar fragment detalle
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, DetalleObraFragment.class, null)
                .addToBackStack(null)
                .commit();
    }

    public Exposicion consultaExposicion(ResultadoFiltro resultado){
        Exposicion expoConsultada;
        //Se obtiene todos los datos de la exposicion desde base de datos
        //SELECT * FROM Exposicion WHERE id= resultado.getId()
        //Se guarda en una variable y se retorna
        //return expoConsultada;
        return new Exposicion();
    }

}