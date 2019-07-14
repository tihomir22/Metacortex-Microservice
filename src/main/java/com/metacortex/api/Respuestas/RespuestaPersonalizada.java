package com.metacortex.api.Respuestas;

import com.metacortex.api.entidades.PrecioActivo;

public class RespuestaPersonalizada extends RespuestaBase {

    private PrecioActivo data;

    public RespuestaPersonalizada(int estado, String mensaje, PrecioActivo data) {
        super(estado, mensaje);
        this.data = data;
    }

    public RespuestaPersonalizada(int estado, String mensaje) {
        super(estado, mensaje);
    }

    public RespuestaPersonalizada() {
    }

    public RespuestaPersonalizada(PrecioActivo data) {
        this.data = data;
    }

    public PrecioActivo getData() {
        return data;
    }

    public void setData(PrecioActivo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespuestaPersonalizada{" +
                "data=" + data +
                '}';
    }
}
