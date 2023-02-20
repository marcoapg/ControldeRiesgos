package com.usat.controlderiesgos.ui.vulnerabilidad;

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
import com.usat.controlderiesgos.Model.Vulnerabilidad;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.Vulnerabilidad;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentVulnerabilidadEditBinding;
import com.usat.controlderiesgos.ui.vulnerabilidad.VulnerabilidadEditFragment;
import com.usat.controlderiesgos.ui.vulnerabilidad.VulnerabilidadFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VulnerabilidadEditFragment extends Fragment {

    
    private TextInputEditText vulnerabilidadIdEdt, vulnerabilidadDescEdt;
    
    private ArrayList<Vulnerabilidad> vulnerabilidadArrayList;
    
    private int vulnerabilidadId;
    
    private FragmentVulnerabilidadEditBinding binding;




    public VulnerabilidadEditFragment() {

    }


    public static VulnerabilidadEditFragment newInstance(String param1, String param2) {
        VulnerabilidadEditFragment fragment = new VulnerabilidadEditFragment();
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

        binding = FragmentVulnerabilidadEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editVulnerabilidadBtn = root.findViewById(R.id.idBtnVulnerabilidad);

        vulnerabilidadIdEdt = root.findViewById(R.id.idEditVulnerabilidadID);
        vulnerabilidadDescEdt = root.findViewById(R.id.idEdtVulnerabilidadDescripcion);
        vulnerabilidadArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("VulnerabilidadID",String.valueOf(bundle.getInt("vulnerabilidadid")));
            vulnerabilidadIdEdt.setText(String.valueOf(bundle.getInt("vulnerabilidadid")));
            vulnerabilidadId=bundle.getInt("vulnerabilidadid");
            cargarVulnerabilidad();
        }

        editVulnerabilidadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarVulnerabilidad();
            }
        });

        return root;
    }

    private void actualizarVulnerabilidad(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        Vulnerabilidad objEdit = new Vulnerabilidad(Integer.parseInt(String.valueOf(vulnerabilidadIdEdt.getText())),String.valueOf(vulnerabilidadDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarVulnerabilidad(objEdit);



        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                    VulnerabilidadFragment fragment2 = new VulnerabilidadFragment();

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


    private void cargarVulnerabilidad(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(vulnerabilidadId);

        Call<ArrayList<Vulnerabilidad>> call = pythonAnywhereApi.obtenerVulnerabilidadId(String.valueOf(vulnerabilidadId));

        call.enqueue(new Callback<ArrayList<Vulnerabilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<Vulnerabilidad>> call, Response<ArrayList<Vulnerabilidad>> response) {
                if(response.isSuccessful()){
                    vulnerabilidadArrayList=response.body();
                    Log.i("Descripcion: ", vulnerabilidadArrayList.get(0).getDescripcion());
                    vulnerabilidadDescEdt.setText(vulnerabilidadArrayList.get(0).getDescripcion());
                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vulnerabilidad>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
}