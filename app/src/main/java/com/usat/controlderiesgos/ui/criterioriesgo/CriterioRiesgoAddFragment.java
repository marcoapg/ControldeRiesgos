package com.usat.controlderiesgos.ui.criterioriesgo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.AddRequestDescriptionValue;
import com.usat.controlderiesgos.Model.AddRequestDescriptionValueColor;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.R;
import com.usat.controlderiesgos.databinding.FragmentCriterioRiesgoAddBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriterioRiesgoAddFragment extends Fragment {

    private TextInputEditText criterioriesgoDescEdt, criterioriesgoValEdt, criterioriesgoColEdt;
    private FragmentCriterioRiesgoAddBinding binding;

    public CriterioRiesgoAddFragment() {
        // Required empty public constructor
    }

    public static CriterioRiesgoAddFragment newInstance(String param1, String param2) {
        CriterioRiesgoAddFragment fragment = new CriterioRiesgoAddFragment();
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
        binding = FragmentCriterioRiesgoAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        criterioriesgoDescEdt = root.findViewById(R.id.idEdtCriterioRiesgoDescripcion);
        criterioriesgoValEdt = root.findViewById(R.id.idEdtCriterioRiesgoValor);
        criterioriesgoColEdt = root.findViewById(R.id.idEdtCriterioRiesgoColor);

        criterioriesgoColEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (criterioriesgoColEdt.hasFocus()) {
                    final ColorPicker cp;

                    if(String.valueOf(criterioriesgoColEdt.getText()).length()>0){
                        int colorEdt = Color.parseColor(String.valueOf(criterioriesgoColEdt.getText()));

                        cp = new ColorPicker(getActivity(),Color.red(colorEdt),Color.green(colorEdt),Color.blue(colorEdt));

                    }else{cp = new ColorPicker(getActivity(), 255, 140, 73);}

                    cp.show();
                    cp.enableAutoClose();


                    cp.setCallback(new ColorPickerCallback() {
                        @Override
                        public void onColorChosen(@ColorInt int color) {
                            // Do whatever you want



                            Log.d("Alpha", Integer.toString(Color.alpha(color)));
                            Log.d("Red", Integer.toString(Color.red(color)));
                            Log.d("Green", Integer.toString(Color.green(color)));
                            Log.d("Blue", Integer.toString(Color.blue(color)));

                            Log.d("Pure Hex", Integer.toHexString(color));
                            Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
                            Log.d("#Hex with alpha", String.format("#%08X", (0xFFFFFFFF & color)));

                            criterioriesgoColEdt.setText("");
                            criterioriesgoColEdt.setText(String.format("#%06X", (0xFFFFFF & color)));
                            // If the auto-dismiss option is not enable (disabled as default) you have to manually dimiss the dialog
                            // cp.dismiss();


                        }
                    });
                }
            }
        });

        criterioriesgoColEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ColorPicker cp;

                if(String.valueOf(criterioriesgoColEdt.getText()).length()>0){
                    int colorEdt = Color.parseColor(String.valueOf(criterioriesgoColEdt.getText()));

                    cp = new ColorPicker(getActivity(),Color.red(colorEdt),Color.green(colorEdt),Color.blue(colorEdt));

                }else{cp = new ColorPicker(getActivity(), 255, 140, 73);}

                cp.show();
                cp.enableAutoClose();


                cp.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int color) {
                        // Do whatever you want



                        Log.d("Alpha", Integer.toString(Color.alpha(color)));
                        Log.d("Red", Integer.toString(Color.red(color)));
                        Log.d("Green", Integer.toString(Color.green(color)));
                        Log.d("Blue", Integer.toString(Color.blue(color)));

                        Log.d("Pure Hex", Integer.toHexString(color));
                        Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
                        Log.d("#Hex with alpha", String.format("#%08X", (0xFFFFFFFF & color)));

                        criterioriesgoColEdt.setText("");
                        criterioriesgoColEdt.setText(String.format("#%06X", (0xFFFFFF & color)));
                        // If the auto-dismiss option is not enable (disabled as default) you have to manually dimiss the dialog
                        // cp.dismiss();


                    }
                });
            }
        });

        Button addCriterioRiesgoBtn = root.findViewById(R.id.idBtnAgregarCriterioRiesgo);

        addCriterioRiesgoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCriterioRiesgo();
            }
        });

        return root;
    }

    private void guardarCriterioRiesgo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://controlriesgosusat.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);

        AddRequestDescriptionValueColor objAdd = new AddRequestDescriptionValueColor();

        objAdd.setDescripcion(String.valueOf(criterioriesgoDescEdt.getText()));
        objAdd.setValor(Integer.parseInt(String.valueOf(criterioriesgoValEdt.getText())));
        objAdd.setColor(String.valueOf(criterioriesgoColEdt.getText()));

        Call<ResponsePython> call = pythonAnywhereApi.guardarCriterioRiesgo(objAdd);

        call.enqueue(new Callback<ResponsePython>() {
            @Override
            public void onResponse(Call<ResponsePython> call, Response<ResponsePython> response) {
                if(response.isSuccessful()){
                    ResponsePython obj = response.body();
                    Toast.makeText(getActivity(),obj.getMensaje(),Toast.LENGTH_SHORT).show();

                    CriterioRiesgoFragment fragment2 = new CriterioRiesgoFragment();

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