package com.usat.controlderiesgos.ui.amenaza;

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
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentAmenazaEditBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AmenazaEditFragment extends Fragment {

    private TextInputEditText amenazaIdEdt, amenazaDescEdt;

    Amenaza amenaza;
    private ArrayList<Amenaza> amenazaArrayList;

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
        amenazaArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("AmenazaID",String.valueOf(bundle.getInt("amenazaid")));
            amenazaIdEdt.setText(String.valueOf(bundle.getInt("amenazaid")));
            amenazaId=bundle.getInt("amenazaid");
            cargarAmenaza();
        }

        editAmenazaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAmenaza();
            }
        });

        return root;
    }

    private void actualizarAmenaza(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        Amenaza objEdit = new Amenaza(Integer.parseInt(String.valueOf(amenazaIdEdt.getText())),String.valueOf(amenazaDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarAmenaza(objEdit);



        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                    AmenazaFragment fragment2 = new AmenazaFragment();

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                            .commit();
                }else{
                    Log.i("API","No Success");
                }


            }

            @Override
            public void onFailure(Call<ResponsePython> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void cargarAmenaza(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(amenazaId);

        Call<ArrayList<Amenaza>> call = pythonAnywhereApi.obtenerAmenazaId(String.valueOf(amenazaId));

        call.enqueue(new Callback<ArrayList<Amenaza>>() {
            @Override
            public void onResponse(Call<ArrayList<Amenaza>> call, Response<ArrayList<Amenaza>> response) {
                if(response.isSuccessful()){
                    amenazaArrayList=response.body();
                    Log.i("Descripcion: ", amenazaArrayList.get(0).getDescripcion());
                    amenazaDescEdt.setText(amenazaArrayList.get(0).getDescripcion());
                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Amenaza>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
}