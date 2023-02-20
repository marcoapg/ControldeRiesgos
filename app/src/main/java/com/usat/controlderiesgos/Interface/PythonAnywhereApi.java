package com.usat.controlderiesgos.Interface;

import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.AddRequest;
import com.usat.controlderiesgos.Model.DeleteRequest;
import com.usat.controlderiesgos.Model.ResponsePython;
import com.usat.controlderiesgos.Model.TipoActivo;
import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.Model.Vulnerabilidad;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PythonAnywhereApi {

    /*Amenaza - INICIO*/
    @GET("api_obteneramenazas")
    Call<ArrayList<Amenaza>> getAmenazas();

    @POST("api_eliminaramenaza")
    Call<ResponsePython> eliminarAmenaza(@Body DeleteRequest deleteRequest);

    @POST("api_actualizaramenaza")
    Call<ResponsePython> actualizarAmenaza(@Body Amenaza amenaza);

    @POST("api_guardaramenaza")
    Call<ResponsePython> guardarAmenaza(@Body AddRequest addRequest);

    @GET("api_obteneramenaza/{id}")
    Call<ArrayList<Amenaza>> obtenerAmenazaId(@Path("id") String amenazaid);

    /*Amenaza - FIN*/

    /*TipoActivo - INICIO*/
    @GET("api_obtenertiposactivo")
    Call<ArrayList<TipoActivo>> getTiposActivos();

    @POST("api_eliminartipoactivo")
    Call<ResponsePython> eliminarTipoActivo(@Body DeleteRequest deleteRequest);

    @POST("api_actualizartipoactivo")
    Call<ResponsePython> actualizarTipoActivo(@Body TipoActivo tipoactivo);

    @POST("api_guardartipoactivo")
    Call<ResponsePython> guardarTipoActivo(@Body AddRequest tipoactivoAdd);

    @GET("api_obtenertipoactivo/{id}")
    Call<ArrayList<TipoActivo>> obtenerTipoActivoId(@Path("id") String tipoactivoid);

    /*TipoActivo - FIN*/

    /*Vulnerabilidad - INICIO*/
    @GET("api_obtenervulnerabilidades")
    Call<ArrayList<Vulnerabilidad>> getVulnerabilidades();

    @POST("api_eliminarvulnerabilidad")
    Call<ResponsePython> eliminarVulnerabilidad(@Body DeleteRequest deleteRequest);

    @POST("api_actualizarvulnerabilidad")
    Call<ResponsePython> actualizarVulnerabilidad(@Body Vulnerabilidad vulnerabilidad);

    @POST("api_guardarvulnerabilidad")
    Call<ResponsePython> guardarVulnerabilidad(@Body AddRequest vulnerabilidadAdd);

    @GET("api_obtenervulnerabilidad/{id}")
    Call<ArrayList<Vulnerabilidad>> obtenerVulnerabilidadId(@Path("id") String vulnerabilidadid);

    /*Vulnerabilidad - FIN*/

    /*UnidadOrganizacional - INICIO*/
    @GET("api_obtenerunidadesorganizacionales")
    Call<ArrayList<UnidadOrganizacional>> getUnidadesOrganizacionales();

    @POST("api_eliminarunidadorganizacional")
    Call<ResponsePython> eliminarUnidadOrganizacional(@Body DeleteRequest deleteRequest);

    @POST("api_actualizarunidadorganizacional")
    Call<ResponsePython> actualizarUnidadOrganizacional(@Body UnidadOrganizacional unidadorganizacional);

    @POST("api_guardarunidadorganizacional")
    Call<ResponsePython> guardarUnidadOrganizacional(@Body AddRequest unidadorganizacionalAdd);

    @GET("api_obtenerunidadorganizacional/{id}")
    Call<ArrayList<UnidadOrganizacional>> obtenerUnidadOrganizacionalId(@Path("id") String unidadorganizacionalid);

    /*UnidadOrganizacional - FIN*/



}
