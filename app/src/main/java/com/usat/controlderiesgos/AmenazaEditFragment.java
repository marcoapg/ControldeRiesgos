package com.usat.controlderiesgos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.databinding.FragmentAmenazaEditBinding;


public class AmenazaEditFragment extends Fragment {

    private TextInputEditText amenazaIdEdt, amenazaDescEdt;

    Amenaza amenaza;

    private String amenazaId;

    private FragmentAmenazaEditBinding binding;

    public AmenazaEditFragment() {

    }


    public static AmenazaEditFragment newInstance(String param1, String param2) {
        AmenazaEditFragment fragment = new AmenazaEditFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAmenazaEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editAmenazaBtn = root.findViewById(R.id.idBtnEditarAmenaza);

        amenazaIdEdt = root.findViewById(R.id.idEdtAmenazaID);
        amenazaDescEdt = root.findViewById(R.id.idEdtAmenazaDescripcion);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("AmenazaID",String.valueOf(bundle.getInt("amenazaid")));
            amenazaIdEdt.setText(String.valueOf(bundle.getInt("amenazaid")));
        }


        return root;
    }
}