package com.usat.controlderiesgos.Model;

public class TipoActivo {
    private int TipoActivoID;
    private String Descripcion;


     public int getTipoActivoID() {
            return TipoActivoID;
        }

        public void setTipoActivoID(int tipoActivoID) {
            TipoActivoID = tipoActivoID;
        }

        public String getDescripcion() {
            return Descripcion;
        }

        public void setDescripcion(String descripcion) {
            Descripcion = descripcion;
        }
}
