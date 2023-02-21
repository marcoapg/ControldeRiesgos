package com.usat.controlderiesgos.ui.criterioprobabilidad;

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
import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioProbabilidadBinding;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriterioProbabilidadFragment extends Fragment implements CriterioProbabilidadAdapter.CriterioProbabilidadClickInterface{

    private RecyclerView recyclerView;
    private ArrayList<CriterioProbabilidad> criterioprobabilidadArrayList;
    private FragmentCriterioProbabilidadBinding binding;
    private CriterioProbabilidadAdapter criterioprobabilidadAdapter;
    private FloatingActionButton addBtn;
    private SearchView svBuscar;

    private boolean searchOn;
    private RelativeLayout homeRL;
    public static CriterioProbabilidadFragment newInstance() {
        return new CriterioProbabilidadFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CriterioProbabilidadViewModel criterioprobabilidadViewModel =
                new ViewModelProvider(this).get(CriterioProbabilidadViewModel.class);

        binding = FragmentCriterioProbabilidadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeRL = root.findViewById(R.id.idBSL);

//        final TextView textView = binding.textGallery;
//        criterioprobabilidadViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView = root.findViewById(R.id.rvCriterioProbabilidad);
        criterioprobabilidadArrayList = new ArrayList<>();

        criterioprobabilidadAdapter = new CriterioProbabilidadAdapter(criterioprobabilidadArrayList, getActivity(), this::onCriterioProbabilidadClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(criterioprobabilidadAdapter);

        addBtn = root.findViewById(R.id.addCriterioProbabilidad);
        svBuscar = root.findViewById(R.id.buscarCriterioProbabilidad);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CriterioProbabilidadAddFragment fragment2 = new CriterioProbabilidadAddFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }
        });

        viewJsonData(this::onCriterioProbabilidadClick, searchOn = false, "");

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getQuery(String s) {

        if (s.equals("change")) {
            viewJsonData(this::onCriterioProbabilidadClick, searchOn = false, s);
            Log.i("get", "false");
        } else {
            viewJsonData(this::onCriterioProbabilidadClick, searchOn = true, s);
            Log.i("get", "true");
        }

    }

    private void viewJsonData(CriterioProbabilidadAdapter.CriterioProbabilidadClickInterface criterioprobabilidadClickInterface, boolean searchOn, String s) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<CriterioProbabilidad>> call = pythonAnywhereApi.getCriteriosProbabilidad();
        call.enqueue(new Callback<ArrayList<CriterioProbabilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<CriterioProbabilidad>> call, Response<ArrayList<CriterioProbabilidad>> response) {
                criterioprobabilidadArrayList = response.body();
                int i = 0;
                LinearLayoutManager manager;
                CriterioProbabilidadAdapter adapter;
                manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                ArrayList<CriterioProbabilidad> criterioprobabilidadsFiltro = new ArrayList<>();
                ;

                for (i = 0; i < criterioprobabilidadArrayList.size(); i++) {
                    if (searchOn) {
                        if (criterioprobabilidadArrayList.get(i).getDescripcion().toLowerCase().startsWith(s.toLowerCase())) {
                            CriterioProbabilidad coincide = criterioprobabilidadArrayList.get(i);
                            criterioprobabilidadsFiltro.add(coincide);
//                            criterioprobabilidadArrayList.clear();
//                            criterioprobabilidadArrayList.add(coincide);


                        }

                    } else {
                        adapter = new CriterioProbabilidadAdapter(criterioprobabilidadArrayList, getActivity(), criterioprobabilidadClickInterface);
                        recyclerView.setAdapter(adapter);
                    }


                }

                if (searchOn) {
                    for (int j = 0; j < criterioprobabilidadsFiltro.size(); j++) {
                        adapter = new CriterioProbabilidadAdapter(criterioprobabilidadsFiltro, getActivity(), criterioprobabilidadClickInterface);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CriterioProbabilidad>> call, Throwable t) {

            }
        });
    }

    private void displayBottomSheet(CriterioProbabilidad criterioprobabilidad) {

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView criterioprobabilidadIdTV = layout.findViewById(R.id.idTVId);
        TextView criterioprobabilidadDescripcionTV = layout.findViewById(R.id.idTVDescripcion);
        TextView criterioprobabilidadValorTV = layout.findViewById(R.id.idTVValor);


        criterioprobabilidadValorTV.setVisibility(View.VISIBLE);

        criterioprobabilidadIdTV.setText(String.valueOf(criterioprobabilidad.getCriterioprobabilidadid()));
        criterioprobabilidadDescripcionTV.setText(criterioprobabilidad.getDescripcion());
        criterioprobabilidadValorTV.setText(String.valueOf(criterioprobabilidad.getValor()));

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetTeachersDialog.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("criterioprobabilidadid", criterioprobabilidad.getCriterioprobabilidadid());

                CriterioProbabilidadEditFragment fragment2 = new CriterioProbabilidadEditFragment();
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
                eliminarRegistro(criterioprobabilidad);
                bottomSheetTeachersDialog.cancel();

            }
        });

    }

    private void eliminarRegistro(CriterioProbabilidad objCriterioProbabilidad) {

        int idEliminar = objCriterioProbabilidad.getCriterioprobabilidadid();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(idEliminar);

        Call<ResponsePython> call = pythonAnywhereApi.eliminarCriterioProbabilidad(obj);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                ResponsePython obj = response.body();
                Toast.makeText(getActivity(), obj.getMensaje(), Toast.LENGTH_SHORT).show();
                CriterioProbabilidadFragment fragment2 = new CriterioProbabilidadFragment();

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



    public void onCriterioProbabilidadClick(int position) {
        displayBottomSheet(criterioprobabilidadArrayList.get(position));
    }

}