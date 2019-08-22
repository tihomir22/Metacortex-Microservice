package com.metacortex.api.Respuestas;


import com.metacortex.api.entidades.TechnicalRegistry;

public class RespuestaIndicadorTecnico extends RespuestaBase{
    private TechnicalRegistry[][] listaTecnico;

    public RespuestaIndicadorTecnico(int estado, String mensaje, TechnicalRegistry[][] listaTecnico) {
        super(estado, mensaje);
        this.listaTecnico = listaTecnico;
    }

    public TechnicalRegistry[][] getListaTecnico() {
        return listaTecnico;
    }

    public RespuestaIndicadorTecnico(){}

    public void setListaTecnico(TechnicalRegistry[][] listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    @Override
    public String toString() {
        return "RespuestaIndicadorTecnico{" +
                "listaTecnico=" + listaTecnico +
                '}';
    }
}
