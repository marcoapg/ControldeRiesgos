package com.usat.controlderiesgos.Model;

public class AddRiesgo {
    private String descripcion;
    private int activoid;

    private int vulnerabilidadid;
    private int amenazaid;

    public AddRiesgo(String descripcion, int activoid, int vulnerabilidadid, int amenazaid) {
        this.descripcion = descripcion;
        this.activoid = activoid;
        this.vulnerabilidadid = vulnerabilidadid;
        this.amenazaid = amenazaid;
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
