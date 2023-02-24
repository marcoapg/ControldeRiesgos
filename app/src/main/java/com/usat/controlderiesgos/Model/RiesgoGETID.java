package com.usat.controlderiesgos.Model;

public class RiesgoGETID {

    private int riesgoid;

    private String descripcion;

    private int activo;

    private int vulnerabilidad;

    private int amenaza;

    public RiesgoGETID(int riesgoid, String descripcion, int activo, int vulnerabilidad, int amenaza) {
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getVulnerabilidad() {
        return vulnerabilidad;
    }

    public void setVulnerabilidad(int vulnerabilidad) {
        this.vulnerabilidad = vulnerabilidad;
    }

    public int getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(int amenaza) {
        this.amenaza = amenaza;
    }
}
