package com.metacortex.api.Respuestas;

import com.metacortex.api.entidades.HistoricDataWrapper;

public class RespuestaPersonalizadaHistorico extends RespuestaBase{

    private HistoricDataWrapper data;

    public RespuestaPersonalizadaHistorico(int estado, String mensaje, HistoricDataWrapper data) {
        super(estado, mensaje);
        this.data = data;
    }

    public RespuestaPersonalizadaHistorico(HistoricDataWrapper data) {
        this.data = data;
    }

    public RespuestaPersonalizadaHistorico() {
    }

    public RespuestaPersonalizadaHistorico(int estado, String mensaje) {
        super(estado, mensaje);
    }

    public HistoricDataWrapper getData() {
        return data;
    }

    public void setData(HistoricDataWrapper data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespuestaPersonalizadaHistorico{" +
                "data=" + data +
                '}';
    }
}
