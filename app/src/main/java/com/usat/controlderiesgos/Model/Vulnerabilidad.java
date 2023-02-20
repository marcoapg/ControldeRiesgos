package com.usat.controlderiesgos.Model;

public class Vulnerabilidad {

    private int VulnerabilidadID;
    private String Descripcion;

    public Vulnerabilidad(int VulnerabilidadID, String Descripcion) {
        this.VulnerabilidadID = VulnerabilidadID;
        this.Descripcion = Descripcion;
    }

    public int getVulnerabilidadID() {
        return VulnerabilidadID;
    }

    public void setVulnerabilidadID(int vulnerabilidadID) {
        VulnerabilidadID = vulnerabilidadID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
