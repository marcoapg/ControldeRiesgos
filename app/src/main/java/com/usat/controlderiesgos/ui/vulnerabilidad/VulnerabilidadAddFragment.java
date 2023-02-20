package com.usat.controlderiesgos.ui.vulnerabilidad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VulnerabilidadAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VulnerabilidadAddFragment extends Fragment {

    public VulnerabilidadAddFragment() {

    }

    public static VulnerabilidadAddFragment newInstance(String param1, String param2) {
        VulnerabilidadAddFragment fragment = new VulnerabilidadAddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vulnerabilidad_add, container, false);
    }
}