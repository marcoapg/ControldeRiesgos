package com.usat.controlderiesgos.ui.criterioprobabilidad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;

public class CriterioProbabilidadFragment extends Fragment {

    private CriterioProbabilidadViewModel mViewModel;

    public static CriterioProbabilidadFragment newInstance() {
        return new CriterioProbabilidadFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_criterio_probabilidad, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CriterioProbabilidadViewModel.class);
        // TODO: Use the ViewModel
    }

}