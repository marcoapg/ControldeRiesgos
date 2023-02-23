package com.usat.controlderiesgos.ui.riesgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;

public class RiesgoAddFragment extends Fragment {


    public RiesgoAddFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RiesgoAddFragment newInstance(String param1, String param2) {
        RiesgoAddFragment fragment = new RiesgoAddFragment();

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
        return inflater.inflate(R.layout.fragment_riesgo_add, container, false);
    }
}