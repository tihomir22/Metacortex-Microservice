package com.metacortex.api.Validaciones;

import com.metacortex.api.entidades.IndicadorTecnico;
import com.metacortex.api.entidades.TipoDatoHistorico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaticTools {
    public static int buscarIntervalo(ArrayList<TipoDatoHistorico> lista, String intervalo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPeriodo().equals(intervalo)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarIndicador(ArrayList<IndicadorTecnico> indicadorTecnicos, String indicadorBuscado, int intervalo, String periodoDatosHistoricos, String tipoSeries) {
        for (int i = 0; i < indicadorTecnicos.size(); i++) {
            IndicadorTecnico indicadorTecnico = indicadorTecnicos.get(i);
            if (indicadorTecnico.getIndicatorName().equalsIgnoreCase(indicadorBuscado) &&
                    indicadorTecnico.getIntervalo() == intervalo &&
                    indicadorTecnico.getPeriodoDatosHistoricos().equalsIgnoreCase(periodoDatosHistoricos) &&
                    indicadorTecnico.getTipoSeries().equalsIgnoreCase(tipoSeries)) {
                return i;
            }
        }
        return -1;
    }

    public static String getTechnicalIndicatorURL(String technicalIndicator, Map<String, String> queryParameters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ValidacionesEstaticas.URLLOCAL);
        stringBuilder.append(ValidacionesEstaticas.endPointTEchnical);
        stringBuilder.append(technicalIndicator + "?");
        stringBuilder.append(ValidacionesEstaticas.nombreParBase + "=" + queryParameters.get(ValidacionesEstaticas.nombreParBase));
        stringBuilder.append("&");
        stringBuilder.append(ValidacionesEstaticas.nombreParContra + "=" + queryParameters.get(ValidacionesEstaticas.nombreParContra));
        stringBuilder.append("&");
        stringBuilder.append(ValidacionesEstaticas.intervaloHistorico + "=" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico));
        stringBuilder.append("&");
        stringBuilder.append(ValidacionesEstaticas.intervaloPeriodoIndicador + "=" + queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador));
        stringBuilder.append("&");
        stringBuilder.append(ValidacionesEstaticas.tipoSeriesIndicador + "=" + queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
        return stringBuilder.toString();
    }
}
