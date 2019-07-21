package com.metacortex.api.Respuestas;

import com.metacortex.api.entidades.TechnicalRegistry;

import java.util.ArrayList;

public class RespuestaIndicadorTecnico extends RespuestaBase{
    private ArrayList<TechnicalRegistry> listaTecnico;

    public RespuestaIndicadorTecnico(int estado, String mensaje, ArrayList<TechnicalRegistry> listaTecnico) {
        super(estado, mensaje);
        this.listaTecnico = listaTecnico;
    }

    public RespuestaIndicadorTecnico(int estado, String mensaje) {
        super(estado, mensaje);
    }

    public RespuestaIndicadorTecnico() {
    }

    public RespuestaIndicadorTecnico(ArrayList<TechnicalRegistry> listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    public ArrayList<TechnicalRegistry> getListaTecnico() {
        return listaTecnico;
    }

    public void setListaTecnico(ArrayList<TechnicalRegistry> listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    @Override
    public String toString() {
        return "RespuestaIndicadorTecnico{" +
                "listaTecnico=" + listaTecnico +
                '}';
    }
}
