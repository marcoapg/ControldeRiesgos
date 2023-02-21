package com.usat.controlderiesgos.ui.unidadorganizacional;

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
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentUnidadOrganizacionalBinding;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnidadOrganizacionalFragment extends Fragment implements UnidadOrganizacionalAdapter.UnidadOrganizacionalClickInterface {

    private UnidadOrganizacionalViewModel mViewModel;

    private RecyclerView recyclerView;
    private ArrayList<UnidadOrganizacional> unidadorganizacionalArrayList;
    private FragmentUnidadOrganizacionalBinding binding;
    private UnidadOrganizacionalAdapter unidadorganizacionalAdapter;
    private FloatingActionButton addBtn;
    private SearchView svBuscar;

    private boolean searchOn;
    private RelativeLayout homeRL;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UnidadOrganizacionalViewModel unidadorganizacionalViewModel =
                new ViewModelProvider(this).get(UnidadOrganizacionalViewModel.class);

        binding = FragmentUnidadOrganizacionalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeRL = root.findViewById(R.id.idBSL);

//        final TextView textView = binding.textGallery;
//        unidadorganizacionalViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView = root.findViewById(R.id.rvUnidadOrganizacional);
        unidadorganizacionalArrayList = new ArrayList<>();

        unidadorganizacionalAdapter = new UnidadOrganizacionalAdapter(unidadorganizacionalArrayList, getActivity(), this::onUnidadOrganizacionalClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(unidadorganizacionalAdapter);

        addBtn = root.findViewById(R.id.addUnidadOrganizacional);
        svBuscar = root.findViewById(R.id.buscarUnidadOrganizacional);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnidadOrganizacionalAddFragment fragment2 = new UnidadOrganizacionalAddFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_navigation_drawer, fragment2)
                        .commit();
            }
        });

        viewJsonData(this::onUnidadOrganizacionalClick, searchOn = false, "");

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

    private void getQuery(String s) {

        if (s.equals("change")) {
            viewJsonData(this::onUnidadOrganizacionalClick, searchOn = false, s);
            Log.i("get", "false");
        } else {
            viewJsonData(this::onUnidadOrganizacionalClick, searchOn = true, s);
            Log.i("get", "true");
        }

    }

    private void viewJsonData(UnidadOrganizacionalAdapter.UnidadOrganizacionalClickInterface unidadorganizacionalClickInterface, boolean searchOn, String s) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<ArrayList<UnidadOrganizacional>> call = pythonAnywhereApi.getUnidadesOrganizacionales();
        call.enqueue(new Callback<ArrayList<UnidadOrganizacional>>() {
            @Override
            public void onResponse(Call<ArrayList<UnidadOrganizacional>> call, Response<ArrayList<UnidadOrganizacional>> response) {
                unidadorganizacionalArrayList = response.body();
                int i = 0;
                LinearLayoutManager manager;
                UnidadOrganizacionalAdapter adapter;
                manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                ArrayList<UnidadOrganizacional> unidadesorganizacionalesFiltro = new ArrayList<>();
                ;

                for (i = 0; i < unidadorganizacionalArrayList.size(); i++) {
                    if (searchOn) {
                        if (unidadorganizacionalArrayList.get(i).getDescripcion().toLowerCase().startsWith(s.toLowerCase())) {
                            UnidadOrganizacional coincide = unidadorganizacionalArrayList.get(i);
                            unidadesorganizacionalesFiltro.add(coincide);
//                            unidadorganizacionalArrayList.clear();
//                            unidadorganizacionalArrayList.add(coincide);


                        }

                    } else {
                        adapter = new UnidadOrganizacionalAdapter(unidadorganizacionalArrayList, getActivity(), unidadorganizacionalClickInterface);
                        recyclerView.setAdapter(adapter);
                    }


                }

                if (searchOn) {
                    for (int j = 0; j < unidadesorganizacionalesFiltro.size(); j++) {
                        adapter = new UnidadOrganizacionalAdapter(unidadesorganizacionalesFiltro, getActivity(), unidadorganizacionalClickInterface);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UnidadOrganizacional>> call, Throwable t) {

            }
        });
    }


    private void displayBottomSheet(UnidadOrganizacional unidadorganizacional) {

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, homeRL);

        bottomSheetTeachersDialog.setContentView(layout);

        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        bottomSheetTeachersDialog.show();

        TextView unidadorganizacionalIdTV = layout.findViewById(R.id.idTVId);
        TextView unidadorganizacionalDescripcionTV = layout.findViewById(R.id.idTVDescripcion);

        unidadorganizacionalIdTV.setText(String.valueOf(unidadorganizacional.getUnidadorganizacionalid()));
        unidadorganizacionalDescripcionTV.setText(unidadorganizacional.getDescripcion());

        Button editarBtn = layout.findViewById(R.id.idBtnEditar);
        Button eliminarBtn = layout.findViewById(R.id.idBtnEliminar);

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetTeachersDialog.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("unidadorganizacionalid", unidadorganizacional.getUnidadorganizacionalid());

                UnidadOrganizacionalEditFragment fragment2 = new UnidadOrganizacionalEditFragment();
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
                eliminarRegistro(unidadorganizacional);
                bottomSheetTeachersDialog.cancel();

            }
        });

    }

    private void eliminarRegistro(UnidadOrganizacional objUnidadOrganizacional) {

        int idEliminar = objUnidadOrganizacional.getUnidadorganizacionalid();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        DeleteRequest obj = new DeleteRequest();

        obj.setId(idEliminar);

        Call<ResponsePython> call = pythonAnywhereApi.eliminarUnidadOrganizacional(obj);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                ResponsePython obj = response.body();
                Toast.makeText(getActivity(), obj.getMensaje(), Toast.LENGTH_SHORT).show();
                UnidadOrganizacionalFragment fragment2 = new UnidadOrganizacionalFragment();

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

    public void onUnidadOrganizacionalClick(int position) {
        displayBottomSheet(unidadorganizacionalArrayList.get(position));
    }


}