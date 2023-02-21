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
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentUnidadOrganizacionalEditBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UnidadOrganizacionalEditFragment extends Fragment {


    private TextInputEditText unidadorganizacionalIdEdt, unidadorganizacionalDescEdt;

    UnidadOrganizacional unidadorganizacional;
    private ArrayList<UnidadOrganizacional> unidadorganizacionalArrayList;

    private int unidadorganizacionalId;

    private FragmentUnidadOrganizacionalEditBinding binding;
    public UnidadOrganizacionalEditFragment() {
        // Required empty public constructor
    }

    public static UnidadOrganizacionalEditFragment newInstance(String param1, String param2) {
        UnidadOrganizacionalEditFragment fragment = new UnidadOrganizacionalEditFragment();
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
        binding = FragmentUnidadOrganizacionalEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editAmenazaBtn = root.findViewById(R.id.idBtnEditarUnidadOrganizacional);

        unidadorganizacionalIdEdt = root.findViewById(R.id.idEdtUnidadOrganizacionalID);
        unidadorganizacionalDescEdt = root.findViewById(R.id.idEdtUnidadOrganizacionalDescripcion);
        unidadorganizacionalArrayList= new ArrayList<>();


        Bundle bundle = this.getArguments();

        if(bundle != null){
            Log.i("UnidadOrganizacionalID",String.valueOf(bundle.getInt("unidadorganizacionalid")));
            unidadorganizacionalIdEdt.setText(String.valueOf(bundle.getInt("unidadorganizacionalid")));
            unidadorganizacionalId=bundle.getInt("unidadorganizacionalid");
            cargarUnidadOrganizacional();
        }

        editAmenazaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUnidadOrganizacional();
            }
        });

        return root;

    }

    private void actualizarUnidadOrganizacional(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        UnidadOrganizacional objEdit = new UnidadOrganizacional(Integer.parseInt(String.valueOf(unidadorganizacionalIdEdt.getText())),String.valueOf(unidadorganizacionalDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.actualizarUnidadOrganizacional(objEdit);



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


    private void cargarUnidadOrganizacional(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(unidadorganizacionalId);

        Call<ArrayList<UnidadOrganizacional>> call = pythonAnywhereApi.obtenerUnidadOrganizacionalId(String.valueOf(unidadorganizacionalId));

        call.enqueue(new Callback<ArrayList<UnidadOrganizacional>>() {
            @Override
            public void onResponse(Call<ArrayList<UnidadOrganizacional>> call, Response<ArrayList<UnidadOrganizacional>> response) {
                if(response.isSuccessful()){
                    unidadorganizacionalArrayList=response.body();
                    Log.i("Descripcion: ", unidadorganizacionalArrayList.get(0).getDescripcion());
                    unidadorganizacionalDescEdt.setText(unidadorganizacionalArrayList.get(0).getDescripcion());
                }else{
                    Log.i( "Descripcion: ", "xd");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UnidadOrganizacional>> call, Throwable t) {
                Log.i("Fallo", t.getMessage());

            }
        });

    }
}