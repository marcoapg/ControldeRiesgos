package com.usat.controlderiesgos.Model;

public class Riesgo {
    private int riesgoid;
    private String descripcion;
    private int activoid;
    private int vulnerabilidadid;
    private int amenazaid;


    public Riesgo(int riesgoid, String descripcion, int activoid, int vulnerabilidadid, int amenazaid) {
        this.riesgoid = riesgoid;
        this.descripcion = descripcion;
        this.activoid = activoid;
        this.vulnerabilidadid = vulnerabilidadid;
        this.amenazaid = amenazaid;
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

    public int getActivoid() {
        return activoid;
    }

    public void setActivoid(int activoid) {
        this.activoid = activoid;
    }

    public int getVulnerabilidadid() {
        return vulnerabilidadid;
    }

    public void setVulnerabilidadid(int vulnerabilidadid) {
        this.vulnerabilidadid = vulnerabilidadid;
    }

    public int getAmenazaid() {
        return amenazaid;
    }

    public void setAmenazaid(int amenazaid) {
        this.amenazaid = amenazaid;
    }
}
