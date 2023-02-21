package com.usat.controlderiesgos.ui.unidadorganizacional;

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
import com.usat.controlderiesgos.Model.AddRequestOnlyDescription;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentUnidadOrganizacionalAddBinding;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UnidadOrganizacionalAddFragment extends Fragment {

    private TextInputEditText unidadorganizacionalDescEdt;
    private FragmentUnidadOrganizacionalAddBinding binding;

    public UnidadOrganizacionalAddFragment() {
        // Required empty public constructor
    }


    public static UnidadOrganizacionalAddFragment newInstance(String param1, String param2) {
        UnidadOrganizacionalAddFragment fragment = new UnidadOrganizacionalAddFragment();
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
        binding = FragmentUnidadOrganizacionalAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addUnidadOrganizacionalBtn = root.findViewById(R.id.idBtnAgregarUnidadOrganizacional);

        unidadorganizacionalDescEdt = root.findViewById(R.id.idEdtUnidadOrganizacionalDescripcion);

        addUnidadOrganizacionalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarUnidadOrganizacional();
            }
        });


        return root;
    }

    private void guardarUnidadOrganizacional(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AddRequestOnlyDescription objAdd = new AddRequestOnlyDescription();

        objAdd.setDescripcion(String.valueOf(unidadorganizacionalDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.guardarUnidadOrganizacional(objAdd);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();

                    UnidadOrganizacionalFragment fragment2 = new UnidadOrganizacionalFragment();

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