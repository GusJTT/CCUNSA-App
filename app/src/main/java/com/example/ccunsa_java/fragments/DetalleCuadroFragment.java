package com.example.ccunsa_java.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ccunsa_java.R;
import com.example.ccunsa_java.modelos.ObrasViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCuadroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCuadroFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ObrasViewModel obrasModel;

    public DetalleCuadroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleCuadroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCuadroFragment newInstance(String param1, String param2) {
        DetalleCuadroFragment fragment = new DetalleCuadroFragment();
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

        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        TextView txtTituloObra = view.findViewById(R.id.txtTituloResultado);
        ImageView imgDetFotoObra = view.findViewById(R.id.imgDetGenericoFoto);
        TextView txtDetDescripcionObra = view.findViewById(R.id.txtDetExpocisionDescripcion);

        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        // Observar el cuadro seleccionado y actualizar la UI
        obrasModel.getObraSeleccionada().observe(getViewLifecycleOwner(), obra -> {
            if (obra != null) {
                txtTituloObra.setText(obra.getTitulo());
                Glide.with(getContext())
                        .load(obra.getUrlImagen())
                        .centerCrop()
                        .into(imgDetFotoObra);
                txtDetDescripcionObra.setText(obra.getDescripcion());
            }
        });
        return view;
    }
}