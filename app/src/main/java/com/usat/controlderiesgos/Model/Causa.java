package com.usat.controlderiesgos.Model;

public class Causa {
    private int causaid;
    private int riesgoid;
    private String descripcion;

    public Causa(int causaid, int riesgoid, String descripcion) {
        this.causaid = causaid;
        this.riesgoid = riesgoid;
        this.descripcion = descripcion;
    }

    public int getCausaid() {
        return causaid;
    }

    public void setCausaid(int causaid) {
        this.causaid = causaid;
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
}
