package com.metacortex.api.Respuestas;

import com.metacortex.api.entidades.TipoDatoHistorico;

public class RespuestaPersonalizadaHistorico extends RespuestaBase{

    private TipoDatoHistorico data;

    public RespuestaPersonalizadaHistorico(int estado, String mensaje, TipoDatoHistorico data) {
        super(estado, mensaje);
        this.data = data;
    }

    public RespuestaPersonalizadaHistorico(TipoDatoHistorico data) {
        this.data = data;
    }

    public RespuestaPersonalizadaHistorico(int estado, String mensaje) {
        super(estado, mensaje);
    }

    public RespuestaPersonalizadaHistorico() {
    }

    public TipoDatoHistorico getData() {
        return data;
    }

    public void setData(TipoDatoHistorico data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespuestaPersonalizadaHistorico{" +
                "data=" + data +
                '}';
    }
}
