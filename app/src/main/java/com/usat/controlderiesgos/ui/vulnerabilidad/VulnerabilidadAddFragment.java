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
import com.usat.controlderiesgos.Model.AddRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentVulnerabilidadAddBinding;
import com.usat.controlderiesgos.ui.vulnerabilidad.VulnerabilidadAddFragment;
import com.usat.controlderiesgos.ui.vulnerabilidad.VulnerabilidadFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VulnerabilidadAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VulnerabilidadAddFragment extends Fragment {



    private TextInputEditText vulnerabilidadDescEdt;
    private FragmentVulnerabilidadAddBinding binding;


    public VulnerabilidadAddFragment() {
        // Required empty public constructor
    }


    public static VulnerabilidadAddFragment newInstance(String param1, String param2) {
        VulnerabilidadAddFragment fragment = new VulnerabilidadAddFragment();
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


        binding = FragmentVulnerabilidadAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addVulnerabilidadBtn = root.findViewById(R.id.idBtnAgregarVulnerabilidad);

        vulnerabilidadDescEdt = root.findViewById(R.id.idEdtVulnerabilidadDescripcion);

        addVulnerabilidadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarVulnerabilidad();
            }
        });


        return root;
    }

    private void guardarVulnerabilidad(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AddRequest objAdd = new AddRequest();

        objAdd.setDescripcion(String.valueOf(vulnerabilidadDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.guardarVulnerabilidad(objAdd);

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
}