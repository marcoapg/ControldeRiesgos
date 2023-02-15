package com.usat.controlderiesgos.Model;

public class Amenaza {

    private int amenazaid;
    private String descripcion;

    public Amenaza(int amenazaid, String descripcion) {
        this.amenazaid = amenazaid;
        this.descripcion = descripcion;
    }

    public int getAmenazaid() {
        return amenazaid;
    }

    public void setAmenazaid(int amenazaid) {
        this.amenazaid = amenazaid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
