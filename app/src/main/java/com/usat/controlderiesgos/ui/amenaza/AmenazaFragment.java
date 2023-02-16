package com.usat.controlderiesgos.ui.amenaza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.AmenazaAdapter;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentAmenazaBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmenazaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Amenaza> amenazaArrayList;
    private FragmentAmenazaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AmenazaViewModel amenazaViewModel =
                new ViewModelProvider(this).get(AmenazaViewModel.class);

        binding = FragmentAmenazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        amenazaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView= root.findViewById(R.id.rv);
        amenazaArrayList= new ArrayList<>();
        viewJsonData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void viewJsonData(){
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
                    adapter = new AmenazaAdapter(amenazaArrayList,getActivity());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Amenaza>> call, Throwable t) {

            }
        });
    }
}