package com.usat.controlderiesgos.ui.vulnerabilidad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentTipoactivoBinding;
import com.usat.controlderiesgos.databinding.FragmentVulnerabilidadBinding;
import com.usat.controlderiesgos.ui.tipoactivo.TipoActivoViewModel;

public class VulnerabilidadFragment extends Fragment {

    private VulnerabilidadViewModel mViewModel;

    public static VulnerabilidadFragment newInstance() {
        return new VulnerabilidadFragment();
    }

    private FragmentVulnerabilidadBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        VulnerabilidadViewModel vulnerabilidadViewModel =
                new ViewModelProvider(this).get(VulnerabilidadViewModel.class);

        binding = FragmentVulnerabilidadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VulnerabilidadViewModel.class);
        // TODO: Use the ViewModel
    }

}