package com.usat.controlderiesgos.ui.vulnerabilidad;

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
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.Vulnerabilidad;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentTipoactivoBinding;
import com.usat.controlderiesgos.databinding.FragmentVulnerabilidadBinding;
import com.usat.controlderiesgos.ui.tipoactivo.TipoActivoViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VulnerabilidadFragment extends Fragment implements VulnerabilidadAdapter.VulnerabilidadClickInterface {

    private RecyclerView recyclerView;

    private ArrayList<Vulnerabilidad> vulnerabilidadArrayList;

    private FragmentVulnerabilidadBinding binding;

    private VulnerabilidadAdapter vulnerabilidadAdapter;

    private FloatingActionButton addBtn;

    private SearchView svBuscar;

    private boolean searchOn;

    private RelativeLayout homeRL;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        VulnerabilidadViewModel vulnerabilidadViewModel =
                new ViewModelProvider(this).get(VulnerabilidadViewModel.class);

        binding = FragmentVulnerabilidadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeRL = root.findViewById(R.id.idBSL);

        recyclerView = root.findViewById(R.id.rvVulnerabilidad);
        vulnerabilidadArrayList = new ArrayList<>();

        vulnerabilidadAdapter = new VulnerabilidadAdapter(vulnerabilidadArrayList, getActivity(), this::onVulnerabilidadClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(vulnerabilidadAdapter);

        addBtn = root.findViewById(R.id.addVulnerabilidad);

        svBuscar = root.findViewById(R.id.buscarVulnerabilidad);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VulnerabilidadAddFragment fragment = new VulnerabilidadAddFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer,fragment)
                        .commit();
            }
        });

        viewJsonData(this::onVulnerabilidadClick, searchOn=false, "");

        svBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                getQuery(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.length()==0){getQuery("change");}else{getQuery(s);}
                return true;
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getQuery(String s){

        if(s.equals("change")){
            viewJsonData(this::onVulnerabilidadClick,searchOn=false,s);
            Log.i("get","false");
        }else{
            viewJsonData(this::onVulnerabilidadClick,searchOn=true,s);
            Log.i("get","true");
        }

    }

    private void viewJsonData(VulnerabilidadAdapter.VulnerabilidadClickInterface vulnerabilidadClickInterface, boolean searchOn, String s){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<Vulnerabilidad>> call = pythonAnywhereApi.getVulnerabilidades();

        call.enqueue(new Callback<ArrayList<Vulnerabilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<Vulnerabilidad>> call, Response<ArrayList<Vulnerabilidad>> response) {
                vulnerabilidadArrayList = response.body();
                int i=0;
                LinearLayoutManager manager;
                VulnerabilidadAdapter adapter;
                manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                ArrayList<Vulnerabilidad> vulnerabilidadFiltro = new ArrayList<>();

                for (i=0; i<vulnerabilidadArrayList.size(); i++){
                    if(searchOn){
                        if(vulnerabilidadArrayList.get(i).getDescripcion().toLowerCase().startsWith(s.toLowerCase())){
                            Vulnerabilidad coincide = vulnerabilidadArrayList.get(i);
                            vulnerabilidadFiltro.add(coincide);
                        }

                    }else{
                        adapter = new VulnerabilidadAdapter(vulnerabilidadArrayList, getActivity(), vulnerabilidadClickInterface);
                        recyclerView.setAdapter(adapter);

                    }
                }

                if(searchOn){
                    for (int j=0; j<vulnerabilidadFiltro.size(); j++){
                        adapter = new VulnerabilidadAdapter(vulnerabilidadFiltro,getActivity(),vulnerabilidadClickInterface);
                        recyclerView.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Vulnerabilidad>> call, Throwable t) {

            }
        });

    }

    private void displayBottomSheet(Vulnerabilidad vulnerabilidad){

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView vulnerabilidadIdTV = layout.findViewById(R.id.idTVId);
        TextView vulnerabilidadDescripcionTV = layout.findViewById(R.id.idTVDescripcion);

        vulnerabilidadIdTV.setText(String.valueOf(vulnerabilidad.getVulnerabilidadid()));
        vulnerabilidadDescripcionTV.setText(vulnerabilidad.getDescripcion());

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetTeachersDialog.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("vulnerabilidadid",vulnerabilidad.getVulnerabilidadid());

                VulnerabilidadEditFragment fragment2 = new VulnerabilidadEditFragment();
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
                eliminarRegistro(vulnerabilidad);
                bottomSheetTeachersDialog.cancel();

            }
        });

    }

    private void eliminarRegistro(Vulnerabilidad objVulnerabilidad){

        int idEliminar = objVulnerabilidad.getVulnerabilidadid();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(idEliminar);

        Call<ResponsePython> call = pythonAnywhereApi.eliminarVulnerabilidad(obj);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                ResponsePython obj = response.body();
                Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();
                VulnerabilidadFragment fragment2 = new VulnerabilidadFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }

            @Override
            public void onFailure(Call<ResponsePython> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onVulnerabilidadClick(int position){
        displayBottomSheet(vulnerabilidadArrayList.get(position));
    }


}