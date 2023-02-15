package com.usat.controlderiesgos.Interface;

import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.TipoActivo;
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.Model.Vulnerabilidad;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PythonAnywhereApi {

    @GET("api_obteneramenazas")
    Call<ArrayList<Amenaza>> getAmenazas();

    @GET("api_obtenertiposactivo")
    Call<List<TipoActivo>> getTiposActivos();

    @GET("api_obtenerunidadesorganizacionales")
    Call<List<UnidadOrganizacional>> getUnidadesOrganizacionales();

    @GET("api_obtenervulnerabilidades")
    Call<List<Vulnerabilidad>> getVulnerabilidades();

}
