package com.usat.controlderiesgos.ui.riesgo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.AuthRequest;
import com.usat.controlderiesgos.Model.AuthResponse;
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.RiesgoGET;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentRiesgoBinding;
import com.usat.controlderiesgos.databinding.FragmentRiesgoBinding;
import com.usat.controlderiesgos.ui.riesgo.RiesgoFragment;
import com.usat.controlderiesgos.ui.riesgo.RiesgoEditFragment;
import com.usat.controlderiesgos.ui.riesgo.RiesgoAdapter;
import com.usat.controlderiesgos.ui.riesgo.RiesgoAdapter;
import com.usat.controlderiesgos.ui.riesgo.RiesgoAddFragment;
import com.usat.controlderiesgos.ui.riesgo.RiesgoViewModel;
import com.usat.controlderiesgos.ui.riesgo.RiesgoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiesgoFragment extends Fragment implements RiesgoAdapter.RiesgoClickInterface{

    private RecyclerView recyclerView;
    private ArrayList<RiesgoGET> riesgoArrayList;
    private FragmentRiesgoBinding binding;
    private RiesgoAdapter riesgoAdapter;
    private FloatingActionButton addBtn;
    private SearchView svBuscar;

    private String token;

    FirebaseAuth mAuth;

    private boolean searchOn;
    private RelativeLayout homeRL;

    public static RiesgoFragment newInstance() {
        return new RiesgoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        RiesgoViewModel riesgoViewModel =
                new ViewModelProvider(this).get(RiesgoViewModel.class);

        binding = FragmentRiesgoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        homeRL = root.findViewById(R.id.idBSL);

//        final TextView textView = binding.textGallery;
//        riesgoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView = root.findViewById(R.id.rvRiesgo);
        riesgoArrayList = new ArrayList<>();

        riesgoAdapter = new RiesgoAdapter(riesgoArrayList, getActivity(), this::onRiesgoClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(riesgoAdapter);

        addBtn = root.findViewById(R.id.addRiesgo);
        svBuscar = root.findViewById(R.id.buscarRiesgo);
        mAuth = FirebaseAuth.getInstance();

        obtenerJWT(mAuth.getCurrentUser().getEmail(),mAuth.getUid(),this::onRiesgoClick, searchOn = false, "");


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RiesgoAddFragment fragment2 = new RiesgoAddFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }
        });


        //viewJsonData(this::onRiesgoClick, searchOn = false, "");



        svBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i("Submit", s);
                getQuery(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.i("Change", s);
                if (s.length() == 0) {
                    getQuery("change");
                } else {
                    getQuery(s);
                }
                return true;
            }
        });



        return root;        
        
    }



    private void obtenerJWT(String email,String uid,RiesgoAdapter.RiesgoClickInterface riesgoClickInterface, boolean searchOn, String s){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AuthRequest objAuth = new AuthRequest();
        objAuth.setUsername(email);
        objAuth.setPassword(uid);

        Call<AuthResponse> call = pythonAnywhereApi.obtenerToken(objAuth);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()){
                    AuthResponse obj = response.body();


                    token=obj.getAccess_token();
                    Log.i("Success: ",obj.getAccess_token());
                    viewJsonData(riesgoClickInterface,searchOn,s);


                }else{
                    Log.i("No Success", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.i("Failure",t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getQuery(String s) {

        if (s.equals("change")) {
            viewJsonData(this::onRiesgoClick, searchOn = false, s);
            Log.i("get", "false");
        } else {
            viewJsonData(this::onRiesgoClick, searchOn = true, s);
            Log.i("get", "true");
        }

    }

    private void viewJsonData(RiesgoAdapter.RiesgoClickInterface riesgoClickInterface, boolean searchOn, String s) {

        Log.i("Success var: ",token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<RiesgoGET>> call = pythonAnywhereApi.getRiesgosToken("JWT "+token);
        call.enqueue(new Callback<ArrayList<RiesgoGET>>() {
            @Override
            public void onResponse(Call<ArrayList<RiesgoGET>> call, Response<ArrayList<RiesgoGET>> response) {

                riesgoArrayList = response.body();
                int i = 0;
                LinearLayoutManager manager;
                RiesgoAdapter adapter;
                manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                ArrayList<RiesgoGET> riesgosFiltro = new ArrayList<>();


                for (i = 0; i < riesgoArrayList.size(); i++) {
                    if (searchOn) {
                        if (riesgoArrayList.get(i).getDescripcion().toLowerCase().startsWith(s.toLowerCase())) {
                            RiesgoGET coincide = riesgoArrayList.get(i);
                            riesgosFiltro.add(coincide);
//                            riesgoArrayList.clear();
//                            riesgoArrayList.add(coincide);


                        }

                    } else {
                        adapter = new RiesgoAdapter(riesgoArrayList, getActivity(), riesgoClickInterface);
                        recyclerView.setAdapter(adapter);
                    }


                }

                if (searchOn) {
                    for (int j = 0; j < riesgosFiltro.size(); j++) {
                        adapter = new RiesgoAdapter(riesgosFiltro, getActivity(), riesgoClickInterface);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiesgoGET>> call, Throwable t) {

            }
        });
    }

    private void displayBottomSheet(RiesgoGET riesgo) {

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView riesgoIdTV = layout.findViewById(R.id.idTVId);
        TextView riesgoDescripcionTV = layout.findViewById(R.id.idTVDescripcion);
        TextView riesgoVulnerabilidadTV = layout.findViewById(R.id.idTVValor);
        TextView riesgoAmenazaTV = layout.findViewById(R.id.idTVAmenaza);
        TextView riesgoActivoTV = layout.findViewById(R.id.idTVActivo);


        riesgoVulnerabilidadTV.setVisibility(View.VISIBLE);
        riesgoAmenazaTV.setVisibility(View.VISIBLE);
        riesgoActivoTV.setVisibility(View.VISIBLE);

        riesgoIdTV.setText(String.valueOf(riesgo.getRiesgoid()));
        riesgoDescripcionTV.setText(riesgo.getDescripcion());
        riesgoVulnerabilidadTV.setText(String.valueOf(riesgo.getVulnerabilidad()));
        riesgoAmenazaTV.setText(String.valueOf(riesgo.getAmenaza()));
        riesgoActivoTV.setText(String.valueOf(riesgo.getActivo()));

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetTeachersDialog.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("riesgoid", riesgo.getRiesgoid());

                RiesgoEditFragment fragment2 = new RiesgoEditFragment();
                fragment2.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }
        });

        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarRegistro(riesgo);
                bottomSheetTeachersDialog.cancel();

            }
        });

    }

    private void eliminarRegistro(RiesgoGET objRiesgo) {

        int idEliminar = objRiesgo.getRiesgoid();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(idEliminar);

        Call<ResponsePython> call = pythonAnywhereApi.eliminarRiesgo(obj);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                ResponsePython obj = response.body();
                Toast.makeText(getActivity(), obj.getMensaje(), Toast.LENGTH_SHORT).show();
                RiesgoFragment fragment2 = new RiesgoFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }

            @Override
            public void onFailure(Call<ResponsePython> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onRiesgoClick(int position) {
        displayBottomSheet(riesgoArrayList.get(position));
    }
    
    

}