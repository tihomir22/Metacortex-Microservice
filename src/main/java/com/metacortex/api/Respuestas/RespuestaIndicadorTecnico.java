package com.metacortex.api.Respuestas;

import com.metacortex.api.entidades.RegistroTecnico;

import java.util.ArrayList;

public class RespuestaIndicadorTecnico extends RespuestaBase{
    private ArrayList<RegistroTecnico> listaTecnico;

    public RespuestaIndicadorTecnico(int estado, String mensaje, ArrayList<RegistroTecnico> listaTecnico) {
        super(estado, mensaje);
        this.listaTecnico = listaTecnico;
    }

    public RespuestaIndicadorTecnico(int estado, String mensaje) {
        super(estado, mensaje);
    }

    public RespuestaIndicadorTecnico() {
    }

    public RespuestaIndicadorTecnico(ArrayList<RegistroTecnico> listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    public ArrayList<RegistroTecnico> getListaTecnico() {
        return listaTecnico;
    }

    public void setListaTecnico(ArrayList<RegistroTecnico> listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    @Override
    public String toString() {
        return "RespuestaIndicadorTecnico{" +
                "listaTecnico=" + listaTecnico +
                '}';
    }
}
