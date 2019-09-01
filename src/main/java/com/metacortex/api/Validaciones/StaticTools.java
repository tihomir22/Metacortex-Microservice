package com.metacortex.api.Validaciones;

import com.metacortex.api.Excepciones.ActivoNoEncontradoException;
import com.metacortex.api.Respuestas.RespuestaIndicadorTecnico;
import com.metacortex.api.entidades.AssetPrice;
import com.metacortex.api.entidades.HistoricDataWrapper;
import com.metacortex.api.entidades.TechnicalIndicatorWrapper;
import com.metacortex.api.entidades.TechnicalRegistry;
import com.metacortex.api.repositorio.RepositorioActivos;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


public class StaticTools {


    public static int buscarIntervalo(ArrayList<HistoricDataWrapper> lista, String intervalo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPeriod().equals(intervalo)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarIndicador(ArrayList<TechnicalIndicatorWrapper> indicadorTecnicos, String indicadorBuscado, int intervalo, String periodoDatosHistoricos, String tipoSeries) {
        for (int i = 0; i < indicadorTecnicos.size(); i++) {
            TechnicalIndicatorWrapper indicadorTecnico = indicadorTecnicos.get(i);
            if (indicadorTecnico.getIndicatorName().equalsIgnoreCase(indicadorBuscado) &&
                    indicadorTecnico.getInterval() == intervalo &&
                    indicadorTecnico.getHistoricPeriod().equalsIgnoreCase(periodoDatosHistoricos) &&
                    indicadorTecnico.getSeriesType().equalsIgnoreCase(tipoSeries)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarIndicadorSimple(ArrayList<TechnicalIndicatorWrapper> indicadorTecnicos, String indicadorBuscado, String periodoHistorico) {
        for (int i = 0; i < indicadorTecnicos.size(); i++) {
            TechnicalIndicatorWrapper indicadorTecnico = indicadorTecnicos.get(i);
            if (indicadorTecnico.getIndicatorName().equalsIgnoreCase(indicadorBuscado) && indicadorTecnico.getHistoricPeriod().equalsIgnoreCase(periodoHistorico)) {
                return i;
            }
        }
        return -1;
    }

    public static int busquedaHistoricoCompleta(ArrayList<HistoricDataWrapper> lista, String intervalo, String startTime, String endTime, int limit) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPeriod().equals(intervalo) && lista.get(i).getStartTime().equals(startTime) && lista.get(i).getEndTime().equals(endTime) && lista.get(i).getLimit() == limit) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarIndicadorPorQueryParams(ArrayList<TechnicalIndicatorWrapper>indicatoresTecnicos, Map<String,String> queryParams){
        for (int i=0;i<indicatoresTecnicos.size();i++){
            TechnicalIndicatorWrapper indicadorTecnico = indicatoresTecnicos.get(i);
            if(indicadorTecnico.getQueryParameters().equals(queryParams)){
                return i;
            }
        }
        return -1;
    }


    public static int buscarIndicadorSimpleConSeries(ArrayList<TechnicalIndicatorWrapper> indicadorTecnicos, String indicadorBuscado, String periodoHistorico, String seriesType) {
        for (int i = 0; i < indicadorTecnicos.size(); i++) {
            TechnicalIndicatorWrapper indicadorTecnico = indicadorTecnicos.get(i);
            if (indicadorTecnico.getIndicatorName().equalsIgnoreCase(indicadorBuscado) && indicadorTecnico.getHistoricPeriod().equalsIgnoreCase(periodoHistorico) && indicadorTecnico.getSeriesType().equalsIgnoreCase(seriesType)) {
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
        if (queryParameters.get(ValidacionesEstaticas.intervaloHistorico) != null) {
            stringBuilder.append("&");
            stringBuilder.append(ValidacionesEstaticas.intervaloHistorico + "=" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico));
        }
        String startTime = queryParameters.get(ValidacionesEstaticas.HistoricStartTime);
        String endTime = queryParameters.get(ValidacionesEstaticas.HistoricEndTime);
        String limit = queryParameters.get(ValidacionesEstaticas.HistoricLimit);
        if (startTime == null) {
            startTime = "0";
        }
        if (endTime == null) {
            endTime = "0";
        }
        if (limit == null) {
            limit = "1000";
        }
        stringBuilder.append("&startTime=");
        stringBuilder.append(startTime);
        stringBuilder.append("&endTime=");
        stringBuilder.append(endTime);
        stringBuilder.append("&limit=");
        stringBuilder.append(limit);

        if (queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador) != null) {
            stringBuilder.append("&");
            stringBuilder.append(ValidacionesEstaticas.intervaloPeriodoIndicador + "=" + queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador));
        }
        if (queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador) != null) {
            stringBuilder.append("&");
            stringBuilder.append(ValidacionesEstaticas.tipoSeriesIndicador + "=" + queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
        }
        return stringBuilder.toString();
    }

    public static TechnicalRegistry[][] recuperarIndicador(String nombre, Map<String, String> queryParameters, RepositorioActivos repositorioActivos, String tipo) {
        if (ValidacionesEstaticas.validacionGENERAL(queryParameters, tipo)) {
            Optional<AssetPrice> precioMONGO = repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase) + queryParameters.get(ValidacionesEstaticas.nombreParContra));
            if (precioMONGO.isPresent()) {
                int resBusqueda = busquedaIndicadorGeneral(precioMONGO.get(), nombre, tipo, queryParameters);
                if (resBusqueda != -1) {
                    return precioMONGO.get().getIndicatorList().get(resBusqueda).getRawTechnicalData();
                } else {
                    RespuestaIndicadorTecnico obj = new RestTemplate().getForObject(StaticTools.getTechnicalIndicatorURL(nombre.toLowerCase(), queryParameters), RespuestaIndicadorTecnico.class);
                    if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                    return obj.getListaTecnico();
                }
            } else {
                RespuestaIndicadorTecnico obj = new RestTemplate().getForObject(StaticTools.getTechnicalIndicatorURL(nombre.toLowerCase(), queryParameters), RespuestaIndicadorTecnico.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                return obj.getListaTecnico();
            }

        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
    }

    private static int busquedaIndicadorGeneral(AssetPrice precioMongo, String nombre, String tipo, Map<String, String> queryParameters) {
        /*String startTime=OperadorTernarioJS.obtenerStartTime(queryParameters);
        String endTime=OperadorTernarioJS.obtenerEndTime(queryParameters);
        int limit=OperadorTernarioJS.obtenerLimit(queryParameters);

        switch (tipo.toLowerCase()) {
            case "general":
                return StaticTools.buscarIndicador(precioMongo.getIndicatorList(),
                        nombre.toLowerCase(),
                        Integer.parseInt(queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador)),
                        queryParameters.get(ValidacionesEstaticas.intervaloHistorico),
                        queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
            case "simplesinseries":
                return StaticTools.buscarIndicadorSimple(precioMongo.getIndicatorList(), nombre.toLowerCase(), queryParameters.get(ValidacionesEstaticas.intervaloHistorico));
            case "simpleconseries":
                return StaticTools.buscarIndicadorSimpleConSeries(precioMongo.getIndicatorList(), nombre.toLowerCase(), queryParameters.get(ValidacionesEstaticas.intervaloHistorico), queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
            default:
                return -1;
        }*/
        return buscarIndicadorPorQueryParams(precioMongo.getIndicatorList(),queryParameters);
    }
}
