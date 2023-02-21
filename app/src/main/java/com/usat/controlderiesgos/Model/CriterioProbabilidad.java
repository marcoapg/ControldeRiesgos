package com.usat.controlderiesgos.Model;

public class CriterioProbabilidad {
    private int criterioprobabilidadid;
    private String descripcion;
    private int valor;

    public CriterioProbabilidad(int criterioprobabilidadid, String descripcion, int valor) {
        this.criterioprobabilidadid = criterioprobabilidadid;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public int getCriterioprobabilidadid() {
        return criterioprobabilidadid;
    }

    public void setCriterioprobabilidadid(int criterioprobabilidadid) {
        this.criterioprobabilidadid = criterioprobabilidadid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
