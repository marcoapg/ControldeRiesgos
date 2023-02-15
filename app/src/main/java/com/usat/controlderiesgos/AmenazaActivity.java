package com.usat.controlderiesgos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.usat.controlderiesgos.Interface.PythonAnywhereApi;
import com.usat.controlderiesgos.Model.Amenaza;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmenazaActivity extends AppCompatActivity {

    private List<Amenaza> amenazaList;
    List<Amenaza> lista;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenaza);

//        mJsonTextView=findViewById(R.id.jsonText);
//        obtenerAmenazas();

    }


//    private void obtenerAmenazas() {
//        Retrofit retrofit = new Retrofit.Builder()
//               .baseUrl("https://controlriesgosusat.pythonanywhere.com")
//               .addConverterFactory(GsonConverterFactory.create())
//               .build();
//
//        PythonAnywhereApi pythonAnywhereApi = retrofit.create(PythonAnywhereApi.class);
//        Call<List<Amenaza>> call = pythonAnywhereApi.getAmenazas();
//        call.enqueue(new Callback<List<Amenaza>>() {
//            @Override
//            public void onResponse(Call<List<Amenaza>> call, Response<List<Amenaza>> response) {
//                if(!response.isSuccessful()){
//                    Log.i("Error","Codigo: "+response.code());
//                    return;
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Amenaza>> call, Throwable t) {
//                Log.i("Error","Failure: "+t.getMessage());
//
//            }
//        });
//
//
//    }

}