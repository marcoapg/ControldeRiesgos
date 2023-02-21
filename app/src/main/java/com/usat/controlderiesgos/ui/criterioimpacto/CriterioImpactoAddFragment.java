package com.usat.controlderiesgos.ui.criterioimpacto;

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
import com.usat.controlderiesgos.Model.AddRequestOnlyDescription;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioImpactoAddBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriterioImpactoAddFragment extends Fragment {

    private TextInputEditText criterioimpactoDescEdt,criterioimpactoValEdt;
    private FragmentCriterioImpactoAddBinding binding;

    public CriterioImpactoAddFragment() {
        // Required empty public constructor
    }

    public static CriterioImpactoAddFragment newInstance(String param1, String param2) {
        CriterioImpactoAddFragment fragment = new CriterioImpactoAddFragment();
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
        binding = FragmentCriterioImpactoAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addCriterioImpactoBtn = root.findViewById(R.id.idBtnAgregarCriterioImpacto);

        criterioimpactoDescEdt = root.findViewById(R.id.idEdtCriterioImpactoDescripcion);
        criterioimpactoValEdt = root.findViewById(R.id.idEdtCriterioImpactoValor);
        
        addCriterioImpactoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCriterioImpacto();
            }
        });


        return root;
        
        }

    private void guardarCriterioImpacto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AddRequestDescriptionValue objAdd = new AddRequestDescriptionValue();

        objAdd.setDescripcion(String.valueOf(criterioimpactoDescEdt.getText()));
        objAdd.setValor(Integer.parseInt(String.valueOf(criterioimpactoValEdt.getText())));
        
        Call<ResponsePython> call = pythonAnywhereApi.guardarCriterioImpacto(objAdd);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();

                    CriterioImpactoFragment fragment2 = new CriterioImpactoFragment();

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