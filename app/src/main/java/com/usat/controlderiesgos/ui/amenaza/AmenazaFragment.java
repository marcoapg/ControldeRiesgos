package com.usat.controlderiesgos.ui.amenaza;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.usat.controlderiesgos.AmenazaActivity;
import com.usat.controlderiesgos.AmenazaAdapter;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.BottomSheetLayoutBinding;
import com.usat.controlderiesgos.databinding.FragmentAmenazaBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmenazaFragment extends Fragment implements AmenazaAdapter.AmenazaClickInterface {

    private RecyclerView recyclerView;
    private ArrayList<Amenaza> amenazaArrayList;
    private FragmentAmenazaBinding binding;

    private AmenazaAdapter amenazaAdapter;

    private RelativeLayout homeRL;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AmenazaViewModel amenazaViewModel =
                new ViewModelProvider(this).get(AmenazaViewModel.class);

        binding = FragmentAmenazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeRL = root.findViewById(R.id.idBSL);

//        final TextView textView = binding.textGallery;
//        amenazaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView= root.findViewById(R.id.rv);
        amenazaArrayList= new ArrayList<>();

        amenazaAdapter = new AmenazaAdapter(amenazaArrayList, getActivity(), this::onAmenazaClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(amenazaAdapter);

        viewJsonData(this::onAmenazaClick);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void viewJsonData(AmenazaAdapter.AmenazaClickInterface amenazaClickInterface){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<Amenaza>> call = pythonAnywhereApi.getAmenazas();
        call.enqueue(new Callback<ArrayList<Amenaza>>() {
            @Override
            public void onResponse(Call<ArrayList<Amenaza>> call, Response<ArrayList<Amenaza>> response) {
                amenazaArrayList=response.body();
                int i=0;
                LinearLayoutManager manager;
                AmenazaAdapter adapter;
                for (i=0;i<amenazaArrayList.size();i++){

                    manager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new AmenazaAdapter(amenazaArrayList,getActivity(),amenazaClickInterface);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Amenaza>> call, Throwable t) {

            }
        });
    }

    private void displayBottomSheet(Amenaza amenaza){

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView amenazaIdTV = layout.findViewById(R.id.idTVId);
        TextView amenazaDescripcionTV = layout.findViewById(R.id.idTVDescripcion);

        amenazaIdTV.setText(String.valueOf(amenaza.getAmenazaid()));
        amenazaDescripcionTV.setText(amenaza.getDescripcion());

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void onAmenazaClick(int position){
        displayBottomSheet(amenazaArrayList.get(position));
    }


}