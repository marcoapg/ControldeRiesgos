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
import com.usat.controlderiesgos.Model.CriterioImpacto;
import com.usat.controlderiesgos.Model.CriterioImpacto;
import com.usat.controlderiesgos.Model.CriterioImpacto;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioImpactoEditBinding;
import com.usat.controlderiesgos.databinding.FragmentCriterioImpactoEditBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CriterioImpactoEditFragment extends Fragment {


    private TextInputEditText criterioimpactoIdEdt, criterioimpactoDescEdt,criterioimpactoValEdt;

    CriterioImpacto criterioimpacto;
    private ArrayList<CriterioImpacto> criterioimpactoArrayList;

    private int criterioimpactoId;

    private FragmentCriterioImpactoEditBinding binding;

    public CriterioImpactoEditFragment() {
        // Required empty public constructor
    }

    public static CriterioImpactoEditFragment newInstance(String param1, String param2) {
        CriterioImpactoEditFragment fragment = new CriterioImpactoEditFragment();
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
        binding = FragmentCriterioImpactoEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editCriterioImpactoBtn = root.findViewById(R.id.idBtnEditarCriterioImpacto);

        criterioimpactoIdEdt = root.findViewById(R.id.idEdtCriterioImpactoID);
        criterioimpactoDescEdt = root.findViewById(R.id.idEdtCriterioImpactoDescripcion);
        criterioimpactoValEdt = root.findViewById(R.id.idEdtCriterioImpactoValor);

        criterioimpactoArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("CriterioImpactoID",String.valueOf(bundle.getInt("criterioimpactoid")));
            criterioimpactoIdEdt.setText(String.valueOf(bundle.getInt("criterioimpactoid")));
            criterioimpactoId=bundle.getInt("criterioimpactoid");
            cargarCriterioImpacto();
        }

        editCriterioImpactoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCriterioImpacto();
            }
        });

        return root;
    }

    private void actualizarCriterioImpacto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        CriterioImpacto objEdit = new CriterioImpacto(Integer.parseInt(String.valueOf(criterioimpactoIdEdt.getText())),String.valueOf(criterioimpactoDescEdt.getText()),Integer.parseInt(String.valueOf(criterioimpactoValEdt.getText())));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarCriterioImpacto(objEdit);



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


    private void cargarCriterioImpacto(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(criterioimpactoId);

        Call<ArrayList<CriterioImpacto>> call = pythonAnywhereApi.obtenerCriterioImpactoId(String.valueOf(criterioimpactoId));

        call.enqueue(new Callback<ArrayList<CriterioImpacto>>() {
            @Override
            public void onResponse(Call<ArrayList<CriterioImpacto>> call, Response<ArrayList<CriterioImpacto>> response) {
                if(response.isSuccessful()){
                    criterioimpactoArrayList=response.body();
                    Log.i("Descripcion: ", criterioimpactoArrayList.get(0).getDescripcion());
                    criterioimpactoDescEdt.setText(criterioimpactoArrayList.get(0).getDescripcion());
                    criterioimpactoValEdt.setText(String.valueOf(criterioimpactoArrayList.get(0).getValor()));
                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CriterioImpacto>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
    
    
    
}