package com.usat.controlderiesgos.ui.criterioriesgo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;

public class CriterioRiesgoFragment extends Fragment {

    private CriterioRiesgoViewModel mViewModel;

    public static CriterioRiesgoFragment newInstance() {
        return new CriterioRiesgoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_criterio_riesgo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CriterioRiesgoViewModel.class);
        // TODO: Use the ViewModel
    }

}