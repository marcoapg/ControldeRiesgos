package com.usat.controlderiesgos.Model;

public class CriterioRiesgo {
    private int criterioriesgoid;
    private String descripcion;
    private int valor;
    private String color;

    public CriterioRiesgo(int criterioriesgoid, String descripcion, int valor, String color) {
        this.criterioriesgoid = criterioriesgoid;
        this.descripcion = descripcion;
        this.valor = valor;
        this.color = color;
    }

    public int getCriterioriesgoid() {
        return criterioriesgoid;
    }

    public void setCriterioriesgoid(int criterioriesgoid) {
        this.criterioriesgoid = criterioriesgoid;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
