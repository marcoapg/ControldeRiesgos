package com.usat.controlderiesgos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.databinding.FragmentAmenazaEditBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AmenazaEditFragment extends Fragment {

    private TextInputEditText amenazaIdEdt, amenazaDescEdt;

    Amenaza amenaza;

    private int amenazaId;

    private FragmentAmenazaEditBinding binding;



    public AmenazaEditFragment() {

    }


    public static AmenazaEditFragment newInstance(String param1, String param2) {
        AmenazaEditFragment fragment = new AmenazaEditFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAmenazaEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editAmenazaBtn = root.findViewById(R.id.idBtnEditarAmenaza);

        amenazaIdEdt = root.findViewById(R.id.idEdtAmenazaID);
        amenazaDescEdt = root.findViewById(R.id.idEdtAmenazaDescripcion);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("AmenazaID",String.valueOf(bundle.getInt("amenazaid")));
            amenazaIdEdt.setText(String.valueOf(bundle.getInt("amenazaid")));
            amenazaId=bundle.getInt("amenazaid");
            cargarAmenaza();
        }





        return root;
    }

    private void cargarAmenaza(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(amenazaId);

        Call<Amenaza> call = pythonAnywhereApi.obtenerAmenazaId(String.valueOf(amenazaId));

        call.enqueue(new Callback<Amenaza>() {
            @Override
            public void onResponse(Call<Amenaza> call, Response<Amenaza> response) {
                if(response.isSuccessful()){
                    Amenaza obj = response.body();
                    Log.i("Descripcion: ", "entró correctamente xd");
                }else{
                    Log.i( "Descripcion: ", "xd");
                }

            }

            @Override
            public void onFailure(Call<Amenaza> call, Throwable t) {
                    Log.i("Fallo", t.getMessage());
            }
        });

    }
}