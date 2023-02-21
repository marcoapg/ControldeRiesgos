package com.usat.controlderiesgos.ui.criterioprobabilidad;

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
import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioProbabilidadEditBinding;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriterioProbabilidadEditFragment extends Fragment {

    private TextInputEditText criterioprobabilidadIdEdt, criterioprobabilidadDescEdt,criterioprobabilidadValEdt;

    CriterioProbabilidad criterioprobabilidad;
    private ArrayList<CriterioProbabilidad> criterioprobabilidadArrayList;

    private int criterioprobabilidadId;

    private FragmentCriterioProbabilidadEditBinding binding;


    public CriterioProbabilidadEditFragment() {
        // Required empty public constructor
    }

    public static CriterioProbabilidadEditFragment newInstance(String param1, String param2) {
        CriterioProbabilidadEditFragment fragment = new CriterioProbabilidadEditFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
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
        binding = FragmentCriterioProbabilidadEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editCriterioProbabilidadBtn = root.findViewById(R.id.idBtnEditarCriterioProbabilidad);

        criterioprobabilidadIdEdt = root.findViewById(R.id.idEdtCriterioProbabilidadID);
        criterioprobabilidadDescEdt = root.findViewById(R.id.idEdtCriterioProbabilidadDescripcion);
        criterioprobabilidadValEdt = root.findViewById(R.id.idEdtCriterioProbabilidadValor);

        criterioprobabilidadArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("CriterioProbabilidadID",String.valueOf(bundle.getInt("criterioprobabilidadid")));
            criterioprobabilidadIdEdt.setText(String.valueOf(bundle.getInt("criterioprobabilidadid")));
            criterioprobabilidadId=bundle.getInt("criterioprobabilidadid");
            cargarCriterioProbabilidad();
        }

        editCriterioProbabilidadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCriterioProbabilidad();
            }
        });

        return root;
    }
    private void actualizarCriterioProbabilidad(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        CriterioProbabilidad objEdit = new CriterioProbabilidad(Integer.parseInt(String.valueOf(criterioprobabilidadIdEdt.getText())),String.valueOf(criterioprobabilidadDescEdt.getText()),Integer.parseInt(String.valueOf(criterioprobabilidadValEdt.getText())));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarCriterioProbabilidad(objEdit);



        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                    CriterioProbabilidadFragment fragment2 = new CriterioProbabilidadFragment();

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


    private void cargarCriterioProbabilidad(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(criterioprobabilidadId);

        Call<ArrayList<CriterioProbabilidad>> call = pythonAnywhereApi.obtenerCriterioProbabilidadId(String.valueOf(criterioprobabilidadId));

        call.enqueue(new Callback<ArrayList<CriterioProbabilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<CriterioProbabilidad>> call, Response<ArrayList<CriterioProbabilidad>> response) {
                if(response.isSuccessful()){
                    criterioprobabilidadArrayList=response.body();
                    Log.i("Descripcion: ", criterioprobabilidadArrayList.get(0).getDescripcion());
                    criterioprobabilidadDescEdt.setText(criterioprobabilidadArrayList.get(0).getDescripcion());
                    criterioprobabilidadValEdt.setText(String.valueOf(criterioprobabilidadArrayList.get(0).getValor()));
                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CriterioProbabilidad>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
}