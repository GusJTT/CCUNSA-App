package com.example.ccunsa_java;

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

import com.example.ccunsa_java.adaptadores.AdaptadorObras;
import com.example.ccunsa_java.adaptadores.OnObraClickListener;
import com.example.ccunsa_java.modelos.ObrasViewModel;
import com.example.ccunsa_java.objetos.ObraDeArte;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFragment extends Fragment implements OnObraClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerListaObras;
    private AdaptadorObras adaptadorObras;
    private ObrasViewModel obrasModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFragment newInstance(String param1, String param2) {
        ListaFragment fragment = new ListaFragment();
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
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        recyclerListaObras = view.findViewById(R.id.recyclerListaObras2);
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
                .replace(R.id.fragmentContainerView2, DetalleCuadroFragment.class, null)
                .addToBackStack(null)
                .commit();
    }
}