package com.usat.controlderiesgos.Interface;

import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.AmenazaAdd;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.TipoActivo;
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.Model.Vulnerabilidad;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PythonAnywhereApi {

    /*Amenaza - INICIO*/
    @GET("api_obteneramenazas")
    Call<ArrayList<Amenaza>> getAmenazas();

    @POST("api_eliminaramenaza")
    Call<ResponsePython> eliminarAmenaza(@Body DeleteRequest deleteRequest);

    @POST("api_actualizaramenaza")
    Call<ResponsePython> actualizarAmenaza(@Body Amenaza amenaza);

    @POST("api_guardaramenaza")
    Call<ResponsePython> guardarAmenaza(@Body AmenazaAdd amenazaAdd);

    @GET("api_obteneramenaza/{id}")
    Call<ArrayList<Amenaza>> obtenerAmenazaId(@Path("id") String amenazaid);

    /*Amenaza - FIN*/

    /*TipoActivo - INICIO*/
    @GET("api_obtenertiposactivo")
    Call<List<TipoActivo>> getTiposActivos();

    @POST("api_eliminartipoactivo")
    Call<ResponsePython> eliminarTipoActivo(@Body DeleteRequest deleteRequest);

    @POST("api_actualizartipoactivo")
    Call<ResponsePython> actualizarTipoActivo(@Body TipoActivo tipoactivo);

    @POST("api_guardaramenaza")
    Call<ResponsePython> guardarTipoActivo(@Body TipoActivoAdd amenazaAdd);

    @GET("api_obtenertipoactivo/{id}")
    Call<ArrayList<TipoActivo>> obtenerTipoActivoId(@Path("id") String tipoactivoid);

    /*TipoActivo - INICIO*/

    @GET("api_obtenerunidadesorganizacionales")
    Call<List<UnidadOrganizacional>> getUnidadesOrganizacionales();

    @GET("api_obtenervulnerabilidades")
    Call<List<Vulnerabilidad>> getVulnerabilidades();

}
