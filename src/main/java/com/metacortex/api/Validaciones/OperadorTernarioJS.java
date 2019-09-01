package com.metacortex.api.Validaciones;



import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;


public class OperadorTernarioJS {




    public static String obtenerStartTime(Map<String,String>queryParams){
        if(queryParams.get(ValidacionesEstaticas.HistoricStartTime)!=null && NumberUtils.isCreatable(queryParams.get(ValidacionesEstaticas.HistoricStartTime))){
            return (queryParams.get(ValidacionesEstaticas.HistoricStartTime));
        }else{
            return "0";
        }
    }

    public static String obtenerEndTime(Map<String,String>queryParams){
        if(queryParams.get(ValidacionesEstaticas.HistoricEndTime)!=null && NumberUtils.isCreatable(queryParams.get(ValidacionesEstaticas.HistoricEndTime))){
            return queryParams.get(ValidacionesEstaticas.HistoricEndTime);
        }else{
            return "0";
        }
    }

    public static int obtenerLimit(Map<String,String>queryParams){
        if(queryParams.get(ValidacionesEstaticas.HistoricLimit)!=null && NumberUtils.isCreatable(queryParams.get(ValidacionesEstaticas.HistoricLimit))){
            return Integer.parseInt(queryParams.get(ValidacionesEstaticas.HistoricLimit));
        }else{
            return 1000;
        }
    }


}
