package com.usat.controlderiesgos.Interface;

import com.usat.controlderiesgos.Model.AddRequestDescriptionValue;
import com.usat.controlderiesgos.Model.AddRequestDescriptionValueColor;
import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.Model.AddRequestOnlyDescription;
import com.usat.controlderiesgos.Model.CriterioImpacto;
import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.Model.CriterioRiesgo;
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
    Call<ResponsePython> guardarAmenaza(@Body AddRequestOnlyDescription addRequestOnlyDescription);

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
    Call<ResponsePython> guardarTipoActivo(@Body AddRequestOnlyDescription tipoactivoAdd);

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
    Call<ResponsePython> guardarVulnerabilidad(@Body AddRequestOnlyDescription vulnerabilidadAdd);

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
    Call<ResponsePython> guardarUnidadOrganizacional(@Body AddRequestOnlyDescription unidadorganizacionalAdd);

    @GET("api_obtenerunidadorganizacional/{id}")
    Call<ArrayList<UnidadOrganizacional>> obtenerUnidadOrganizacionalId(@Path("id") String unidadorganizacionalid);

    /*UnidadOrganizacional - FIN*/

    /*CriterioImpacto - INICIO*/
    @GET("api_obtenercriteriosimpacto")
    Call<ArrayList<CriterioImpacto>> getCriteriosImpacto();

    @POST("api_eliminarcriterioimpacto")
    Call<ResponsePython> eliminarCriterioImpacto(@Body DeleteRequest deleteRequest);

    @POST("api_actualizarcriterioimpacto")
    Call<ResponsePython> actualizarCriterioImpacto(@Body CriterioImpacto criterioimpacto);

    @POST("api_guardarcriterioimpacto")
    Call<ResponsePython> guardarCriterioImpacto(@Body AddRequestDescriptionValue criterioimpactoAdd);

    @GET("api_obtenercriterioimpacto/{id}")
    Call<ArrayList<CriterioImpacto>> obtenerCriterioImpactoId(@Path("id") String criterioimpactoid);

    /*CriterioImpacto - FIN*/

    /*CriterioProbabilidad - INICIO*/
    @GET("api_obtenercriteriosprobabilidad")
    Call<ArrayList<CriterioProbabilidad>> getCriteriosProbabilidad();

    @POST("api_eliminarcriterioprobabilidad")
    Call<ResponsePython> eliminarCriterioProbabilidad(@Body DeleteRequest deleteRequest);

    @POST("api_actualizarcriterioprobabilidad")
    Call<ResponsePython> actualizarCriterioProbabilidad(@Body CriterioProbabilidad criterioprobabilidad);

    @POST("api_guardarcriterioprobabilidad")
    Call<ResponsePython> guardarCriterioProbabilidad(@Body AddRequestDescriptionValue criterioprobabilidadAdd);

    @GET("api_obtenercriterioprobabilidad/{id}")
    Call<ArrayList<CriterioProbabilidad>> obtenerCriterioProbabilidadId(@Path("id") String criterioprobabilidadid);

    /*CriterioProbabilidad - FIN*/

    /*CriterioRiesgo - INICIO*/
    @GET("api_obtenercriteriosriesgo")
    Call<ArrayList<CriterioRiesgo>> getCriteriosRiesgo();

    @POST("api_eliminarcriterioriesgo")
    Call<ResponsePython> eliminarCriterioRiesgo(@Body DeleteRequest deleteRequest);

    @POST("api_actualizarcriterioriesgo")
    Call<ResponsePython> actualizarCriterioRiesgo(@Body CriterioRiesgo criterioriesgo);

    @POST("api_guardarcriterioriesgo")
    Call<ResponsePython> guardarCriterioRiesgo(@Body AddRequestDescriptionValueColor criterioriesgoAdd);

    @GET("api_obtenercriterioriesgo/{id}")
    Call<ArrayList<CriterioRiesgo>> obtenerCriterioRiesgoId(@Path("id") String criterioriesgoid);

    /*CriterioRiesgo - FIN*/



}
