package com.usat.controlderiesgos.Model;

public class CriterioImpacto {
    private int criterioimpactoid;
    private String descripcion;
    private int valor;

    public CriterioImpacto(int criterioimpactoid, String descripcion, int valor) {
        this.criterioimpactoid = criterioimpactoid;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public int getCriterioimpactoid() {
        return criterioimpactoid;
    }

    public void setCriterioimpactoid(int criterioimpactoid) {
        this.criterioimpactoid = criterioimpactoid;
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
