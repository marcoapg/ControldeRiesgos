package com.usat.controlderiesgos.ui.riesgo;

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
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.RiesgoGETID;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentRiesgoEditBinding;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RiesgoEditFragment extends Fragment {
    
    private TextInputEditText riesgoIdEdt, riesgoDescEdt, riesgoVulnerabilidadEdt, riesgoAmenazaEdt,riesgoActivoEdt;

    Riesgo riesgo;
    private ArrayList<RiesgoGETID> riesgoArrayList;

    private int riesgoId;

    private FragmentRiesgoEditBinding binding;

    public RiesgoEditFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RiesgoEditFragment newInstance(String param1, String param2) {
        RiesgoEditFragment fragment = new RiesgoEditFragment();
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
        binding = FragmentRiesgoEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editRiesgoBtn = root.findViewById(R.id.idBtnEditarRiesgo);

        riesgoIdEdt = root.findViewById(R.id.idEdtRiesgoID);
        riesgoDescEdt = root.findViewById(R.id.idEdtRiesgoDescripcion);
        riesgoVulnerabilidadEdt = root.findViewById(R.id.idEdtRiesgoVulnerabilidad);
        riesgoAmenazaEdt= root.findViewById(R.id.idEdtRiesgoAmenaza);
        riesgoActivoEdt= root.findViewById(R.id.idEdtRiesgoActivo);

        riesgoArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("RiesgoID",String.valueOf(bundle.getInt("riesgoid")));
            riesgoIdEdt.setText(String.valueOf(bundle.getInt("riesgoid")));
            riesgoId=bundle.getInt("riesgoid");
            cargarRiesgo();
        }

        editRiesgoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarRiesgo();
            }
        });

        return root;
    }

    private void actualizarRiesgo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        Riesgo objEdit = new Riesgo(Integer.parseInt(String.valueOf(riesgoIdEdt.getText())),String.valueOf(riesgoDescEdt.getText()),Integer.parseInt(String.valueOf(riesgoActivoEdt.getText())),Integer.parseInt(String.valueOf(riesgoVulnerabilidadEdt.getText())),Integer.parseInt(String.valueOf(riesgoAmenazaEdt.getText())));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarRiesgo(objEdit);



        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                    RiesgoFragment fragment2 = new RiesgoFragment();

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


    private void cargarRiesgo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(riesgoId);

        Call<ArrayList<RiesgoGETID>> call = pythonAnywhereApi.obtenerRiesgoId(String.valueOf(riesgoId));

        call.enqueue(new Callback<ArrayList<RiesgoGETID>>() {
            @Override
            public void onResponse(Call<ArrayList<RiesgoGETID>> call, Response<ArrayList<RiesgoGETID>> response) {
                if(response.isSuccessful()){
                    riesgoArrayList=response.body();
                    Log.i("Descripcion: ", String.valueOf(riesgoArrayList.get(0).getVulnerabilidad()));
                    riesgoDescEdt.setText(riesgoArrayList.get(0).getDescripcion());
                    riesgoVulnerabilidadEdt.setText(String.valueOf(riesgoArrayList.get(0).getVulnerabilidad()));
                    riesgoAmenazaEdt.setText(String.valueOf(riesgoArrayList.get(0).getAmenaza()));
                    riesgoActivoEdt.setText(String.valueOf(riesgoArrayList.get(0).getActivo()));

                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiesgoGETID>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
    
}