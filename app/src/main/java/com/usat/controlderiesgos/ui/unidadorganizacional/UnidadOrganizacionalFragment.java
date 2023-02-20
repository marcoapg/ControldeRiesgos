package com.usat.controlderiesgos.ui.unidadorganizacional;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usat.controlderiesgos.R;

public class UnidadOrganizacionalFragment extends Fragment {

    private UnidadOrganizacionalViewModel mViewModel;

    public static UnidadOrganizacionalFragment newInstance() {
        return new UnidadOrganizacionalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unidad_organizacional, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UnidadOrganizacionalViewModel.class);
        // TODO: Use the ViewModel
    }

}