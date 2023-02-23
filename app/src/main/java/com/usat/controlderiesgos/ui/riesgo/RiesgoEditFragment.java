package com.usat.controlderiesgos.ui.riesgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;


public class RiesgoEditFragment extends Fragment {


    public RiesgoEditFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RiesgoEditFragment newInstance(String param1, String param2) {
        RiesgoEditFragment fragment = new RiesgoEditFragment();
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
        return inflater.inflate(R.layout.fragment_riesgo_edit, container, false);
    }
}