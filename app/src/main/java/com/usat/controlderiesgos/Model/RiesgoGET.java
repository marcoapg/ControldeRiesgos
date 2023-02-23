package com.usat.controlderiesgos.Model;

public class RiesgoGET {

    private int riesgoid;

    private String descripcion;

    private String activo;

    private String vulnerabilidad;

    private String amenaza;

    public RiesgoGET(int riesgoid, String descripcion, String activo, String vulnerabilidad, String amenaza) {
        this.riesgoid = riesgoid;
        this.descripcion = descripcion;
        this.activo = activo;
        this.vulnerabilidad = vulnerabilidad;
        this.amenaza = amenaza;
    }


    public int getRiesgoid() {
        return riesgoid;
    }

    public void setRiesgoid(int riesgoid) {
        this.riesgoid = riesgoid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getVulnerabilidad() {
        return vulnerabilidad;
    }

    public void setVulnerabilidad(String vulnerabilidad) {
        this.vulnerabilidad = vulnerabilidad;
    }

    public String getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(String amenaza) {
        this.amenaza = amenaza;
    }
}
