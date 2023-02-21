package com.usat.controlderiesgos.ui.criterioriesgo;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.CriterioRiesgo;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioRiesgoBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriterioRiesgoFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<CriterioRiesgo> criterioriesgoArrayList;
    private FragmentCriterioRiesgoBinding binding;
    private CriterioRiesgoAdapter criterioriesgoAdapter;
    private FloatingActionButton addBtn;
    private SearchView svBuscar;

    private boolean searchOn;
    private RelativeLayout homeRL;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CriterioRiesgoViewModel criterioriesgoViewModel =
                new ViewModelProvider(this).get(CriterioRiesgoViewModel.class);

        binding = FragmentCriterioRiesgoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeRL = root.findViewById(R.id.idBSL);

//        final TextView textView = binding.textGallery;
//        criterioriesgoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView = root.findViewById(R.id.rvCriterioRiesgo);
        criterioriesgoArrayList = new ArrayList<>();

        criterioriesgoAdapter = new CriterioRiesgoAdapter(criterioriesgoArrayList, getActivity(), this::onCriterioRiesgoClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(criterioriesgoAdapter);

        addBtn = root.findViewById(R.id.addCriterioRiesgo);
        svBuscar = root.findViewById(R.id.buscarCriterioRiesgo);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CriterioRiesgoAddFragment fragment2 = new CriterioRiesgoAddFragment();
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
//                        .commit();
            }
        });

        viewJsonData(this::onCriterioRiesgoClick, searchOn = false, "");

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
            viewJsonData(this::onCriterioRiesgoClick, searchOn = false, s);
            Log.i("get", "false");
        } else {
            viewJsonData(this::onCriterioRiesgoClick, searchOn = true, s);
            Log.i("get", "true");
        }

    }

    private void viewJsonData(CriterioRiesgoAdapter.CriterioRiesgoClickInterface criterioriesgoClickInterface, boolean searchOn, String s) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<CriterioRiesgo>> call = pythonAnywhereApi.getCriteriosRiesgo();
        call.enqueue(new Callback<ArrayList<CriterioRiesgo>>() {
            @Override
            public void onResponse(Call<ArrayList<CriterioRiesgo>> call, Response<ArrayList<CriterioRiesgo>> response) {
                criterioriesgoArrayList = response.body();
                int i = 0;
                LinearLayoutManager manager;
                CriterioRiesgoAdapter adapter;
                manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                ArrayList<CriterioRiesgo> criterioriesgosFiltro = new ArrayList<>();
                ;

                for (i = 0; i < criterioriesgoArrayList.size(); i++) {
                    if (searchOn) {
                        if (criterioriesgoArrayList.get(i).getDescripcion().toLowerCase().startsWith(s.toLowerCase())) {
                            CriterioRiesgo coincide = criterioriesgoArrayList.get(i);
                            criterioriesgosFiltro.add(coincide);
//                            criterioriesgoArrayList.clear();
//                            criterioriesgoArrayList.add(coincide);


                        }

                    } else {
                        adapter = new CriterioRiesgoAdapter(criterioriesgoArrayList, getActivity(), criterioriesgoClickInterface);
                        recyclerView.setAdapter(adapter);
                    }


                }

                if (searchOn) {
                    for (int j = 0; j < criterioriesgosFiltro.size(); j++) {
                        adapter = new CriterioRiesgoAdapter(criterioriesgosFiltro, getActivity(), criterioriesgoClickInterface);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CriterioRiesgo>> call, Throwable t) {

            }
        });
    }

    private void displayBottomSheet(CriterioRiesgo criterioriesgo) {

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView criterioriesgoIdTV = layout.findViewById(R.id.idTVId);
        TextView criterioriesgoDescripcionTV = layout.findViewById(R.id.idTVDescripcion);
        TextView criterioriesgoValorTV = layout.findViewById(R.id.idTVValor);
        LinearLayout criterioriesgoColor = layout.findViewById(R.id.layoutCardColor);

        criterioriesgoValorTV.setVisibility(View.VISIBLE);
        criterioriesgoColor.setVisibility(View.VISIBLE);

        criterioriesgoIdTV.setText(String.valueOf(criterioriesgo.getCriterioriesgoid()));
        criterioriesgoDescripcionTV.setText(criterioriesgo.getDescripcion());
        criterioriesgoValorTV.setText(String.valueOf(criterioriesgo.getValor()));
        criterioriesgoColor.setBackgroundColor(Color.parseColor(criterioriesgo.getColor()));

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetTeachersDialog.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("criterioriesgoid", criterioriesgo.getCriterioriesgoid());

//                CriterioRiesgoEditFragment fragment2 = new CriterioRiesgoEditFragment();
//                fragment2.setArguments(bundle);
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
//                        .commit();
            }
        });

        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarRegistro(criterioriesgo);
                bottomSheetTeachersDialog.cancel();

            }
        });

    }

    private void eliminarRegistro(CriterioRiesgo objCriterioRiesgo) {

        int idEliminar = objCriterioRiesgo.getCriterioriesgoid();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(idEliminar);

        Call<ResponsePython> call = pythonAnywhereApi.eliminarCriterioRiesgo(obj);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                ResponsePython obj = response.body();
                Toast.makeText(getActivity(), obj.getMensaje(), Toast.LENGTH_SHORT).show();
                CriterioRiesgoFragment fragment2 = new CriterioRiesgoFragment();

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

    public void onCriterioRiesgoClick(int position) {
        displayBottomSheet(criterioriesgoArrayList.get(position));
    }



}