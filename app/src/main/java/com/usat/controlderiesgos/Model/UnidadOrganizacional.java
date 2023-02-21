package com.usat.controlderiesgos.Model;

public class UnidadOrganizacional {
    private int unidadorganizacionalid;
    private String descripcion;

    public int getUnidadorganizacionalid() {
        return unidadorganizacionalid;
    }

    public void setUnidadorganizacionalid(int unidadorganizacionalid) {
        this.unidadorganizacionalid = unidadorganizacionalid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UnidadOrganizacional(int unidadorganizacionalid, String descripcion) {
        this.unidadorganizacionalid = unidadorganizacionalid;
        this.descripcion = descripcion;
    }
}
