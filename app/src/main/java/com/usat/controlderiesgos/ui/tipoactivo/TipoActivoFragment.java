package com.usat.controlderiesgos.ui.tipoactivo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.usat.controlderiesgos.databinding.FragmentTipoactivoBinding;
import com.usat.controlderiesgos.databinding.FragmentTipoactivoBinding;

public class TipoActivoFragment extends Fragment {

    private FragmentTipoactivoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TipoActivoViewModel tipoActivoViewModel =
                new ViewModelProvider(this).get(TipoActivoViewModel.class);

        binding = FragmentTipoactivoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        tipoActivoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}