package com.usat.controlderiesgos.Model;

public class TipoActivo {
    private int tipoactivoid;
    private String descripcion;

    public int getTipoactivoid() {
        return tipoactivoid;
    }

    public void setTipoactivoid(int tipoactivoid) {
        this.tipoactivoid = tipoactivoid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoActivo(int tipoactivoid, String descripcion) {
        this.tipoactivoid = tipoactivoid;
        this.descripcion = descripcion;
    }
}
