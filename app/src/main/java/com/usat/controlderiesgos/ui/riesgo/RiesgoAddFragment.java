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
import com.usat.controlderiesgos.Model.AddRequestDescriptionValue;
import com.usat.controlderiesgos.Model.AddRiesgo;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioImpactoAddBinding;
import com.usat.controlderiesgos.databinding.FragmentRiesgoAddBinding;
import com.usat.controlderiesgos.ui.criterioimpacto.CriterioImpactoFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiesgoAddFragment extends Fragment {

    private TextInputEditText riesgoDescEdt, riesgoVulnerabilidadEdt, riesgoAmenazaEdt,riesgoActivoEdt;
    private FragmentRiesgoAddBinding binding;
    public RiesgoAddFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RiesgoAddFragment newInstance(String param1, String param2) {
        RiesgoAddFragment fragment = new RiesgoAddFragment();

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

        binding = FragmentRiesgoAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addRiesgoBtn = root.findViewById(R.id.idBtnAgregarRiesgo);

        riesgoDescEdt = root.findViewById(R.id.idEdtRiesgoDescripcion);
        riesgoVulnerabilidadEdt = root.findViewById(R.id.idEdtRiesgoVulnerabilidad);
        riesgoAmenazaEdt= root.findViewById(R.id.idEdtRiesgoAmenaza);
        riesgoActivoEdt= root.findViewById(R.id.idEdtRiesgoActivo);

        addRiesgoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRiesgo();
            }
        });


        return root;
    }

    private void guardarRiesgo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AddRiesgo objAdd = new AddRiesgo();

        objAdd.setDescripcion(String.valueOf(riesgoDescEdt.getText()));
        objAdd.setVulnerabilidadid(Integer.parseInt(String.valueOf(riesgoVulnerabilidadEdt.getText())));
        objAdd.setAmenazaid(Integer.parseInt(String.valueOf(riesgoAmenazaEdt.getText())));
        objAdd.setActivoid(Integer.parseInt(String.valueOf(riesgoActivoEdt.getText())));

        Call<ResponsePython> call = pythonAnywhereApi.guardarRiesgo(objAdd);

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
}