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
import com.usat.controlderiesgos.Model.AmenazaAdd;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentAmenazaAddBinding;
import com.usat.controlderiesgos.databinding.FragmentAmenazaEditBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AmenazaAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmenazaAddFragment extends Fragment {


    private TextInputEditText amenazaDescEdt;
    private FragmentAmenazaAddBinding binding;


    public AmenazaAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AmenazaAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AmenazaAddFragment newInstance(String param1, String param2) {
        AmenazaAddFragment fragment = new AmenazaAddFragment();
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


        binding = FragmentAmenazaAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addAmenazaBtn = root.findViewById(R.id.idBtnAgregarAmenaza);

        amenazaDescEdt = root.findViewById(R.id.idEdtAmenazaDescripcion);

        addAmenazaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarAmenaza();
            }
        });


        return root;
    }

    private void guardarAmenaza(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AmenazaAdd objAdd = new AmenazaAdd();

        objAdd.setDescripcion(String.valueOf(amenazaDescEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.guardarAmenaza(objAdd);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                    androidx.fragment.app.FragmentTransaction refresh = getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_navigation_drawer, new AmenazaFragment());
                    refresh.commit();
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