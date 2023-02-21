package com.usat.controlderiesgos.Model;

public class Activo {
    private int activoid;
    private String descripcion;
    private int tipoactivoid;
    private int unidadorganizacionalid;

    public Activo(int activoid, String descripcion, int tipoactivoid, int unidadorganizacionalid) {
        this.activoid = activoid;
        this.descripcion = descripcion;
        this.tipoactivoid = tipoactivoid;
        this.unidadorganizacionalid = unidadorganizacionalid;
    }

    public int getActivoid() {
        return activoid;
    }

    public void setActivoid(int activoid) {
        this.activoid = activoid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoactivoid() {
        return tipoactivoid;
    }

    public void setTipoactivoid(int tipoactivoid) {
        this.tipoactivoid = tipoactivoid;
    }

    public int getUnidadorganizacionalid() {
        return unidadorganizacionalid;
    }

    public void setUnidadorganizacionalid(int unidadorganizacionalid) {
        this.unidadorganizacionalid = unidadorganizacionalid;
    }
}
