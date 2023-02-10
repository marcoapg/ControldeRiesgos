package com.usat.controlderiesgos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmenazaActivity extends AppCompatActivity {

    private TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenaza);
        mJsonTextView=findViewById(R.id.jsonText);
        obtenerAmenazas();
    }

    private void obtenerAmenazas() {
        Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://controlriesgosusat.pythonanywhere.com")
               .addConverterFactory(GsonConverterFactory.create())
               .build();

        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
        Call<List<Amenaza>> call = pythonAnywhereApi.getAmenazas();
        call.enqueue(new Callback<List<Amenaza>>() {
            @Override
            public void onResponse(Call<List<Amenaza>> call, Response<List<Amenaza>> response) {
                if(!response.isSuccessful()){
                    mJsonTextView.setText("Codigo: "+response.code());
                    return;
                }
                List<Amenaza> amenazaList = response.body();
                for (Amenaza amenaza:amenazaList){
                    String content = "";
                    content += "AmenazaID: " + amenaza.getAmenazaid() + "\n";
                    content += "Descripcion: " + amenaza.getDescripcion() + "\n\n";
                    mJsonTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Amenaza>> call, Throwable t) {
                mJsonTextView.setText(t.getMessage());

            }
        });

        
    }

}