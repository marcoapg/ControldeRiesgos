package com.usat.controlderiesgos.Model;

public class Vulnerabilidad {

    private int vulnerabilidadid;
    private String descripcion;


    public Vulnerabilidad(int vulnerabilidadid, String descripcion) {
        this.vulnerabilidadid = vulnerabilidadid;
        this.descripcion = descripcion;
    }

    public int getVulnerabilidadid() {
        return vulnerabilidadid;
    }

    public void setVulnerabilidadid(int vulnerabilidadid) {
        this.vulnerabilidadid = vulnerabilidadid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
