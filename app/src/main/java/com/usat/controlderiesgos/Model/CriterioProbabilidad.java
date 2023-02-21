package com.usat.controlderiesgos.Model;

public class CriterioProbabilidad {
    private int criterioprobabilidad;
    private String descripcion;
    private int valor;

    public CriterioProbabilidad(int criterioprobabilidad, String descripcion, int valor) {
        this.criterioprobabilidad = criterioprobabilidad;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public int getCriterioprobabilidad() {
        return criterioprobabilidad;
    }

    public void setCriterioprobabilidad(int criterioprobabilidad) {
        this.criterioprobabilidad = criterioprobabilidad;
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
