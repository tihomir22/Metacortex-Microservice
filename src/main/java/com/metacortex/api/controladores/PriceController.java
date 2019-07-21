package com.metacortex.api.controladores;

import com.metacortex.api.Excepciones.ActivoNoEncontradoException;
import com.metacortex.api.ModelosResultado.OnlyPrice;
import com.metacortex.api.Respuestas.RespuestaIndicadorTecnico;
import com.metacortex.api.Respuestas.RespuestaPersonalizada;
import com.metacortex.api.Respuestas.RespuestaPersonalizadaHistorico;
import com.metacortex.api.Validaciones.StaticTools;
import com.metacortex.api.Validaciones.ValidacionesEstaticas;
import com.metacortex.api.entidades.*;
import com.metacortex.api.repositorio.RepositorioActivos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class PriceController {

    @Autowired
    private RepositorioActivos repositorioActivos;

    @ApiIgnore
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE,name = "Sambomba")
    public List<AssetPrice> getAll() {
        List<AssetPrice> lista = this.repositorioActivos.findAll();
        return lista;
    }


    @ApiOperation(value = "Live Price", notes = "Returns the live price data about a cryptocurrency asset")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/price",params = {"base","counter"},produces = MediaType.APPLICATION_JSON_VALUE,name = "Sambomba2" )
    public OnlyPrice getPrice(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        Optional<OnlyPrice> res = null;

        if (ValidacionesEstaticas.esPeticionDePrecio(queryParameters)) {
            Optional<AssetPrice> precioMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase) + queryParameters.get(ValidacionesEstaticas.nombreParContra));
            if (precioMONGO.isPresent()) {
                res = Optional.of(new OnlyPrice(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase(), precioMONGO.get().getPrice(), new Date().getTime()));
            } else {
                RespuestaPersonalizada obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + ValidacionesEstaticas.endPointPrecio + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra), RespuestaPersonalizada.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                res = Optional.of(new OnlyPrice(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase(), obj.getData().getPrice(), new Date().getTime()));
            }
        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
        return res.get();
    }

    @ApiOperation(value = "Historic data", notes = "Get the historic data for a cryptocurrency")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/historic",params = {"base","counter","historicInterval"},produces = MediaType.APPLICATION_JSON_VALUE )
    public HistoricDataWrapper getHistoric(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        Optional<HistoricDataWrapper> resHistorico = null;
        if (ValidacionesEstaticas.esPeticionHistoric(queryParameters)) {
            Optional<AssetPrice> activoMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase());
            if (activoMONGO.isPresent()) {
                ArrayList<HistoricDataWrapper> lista = activoMONGO.get().getHistoricData();
                if (lista == null) lista = new ArrayList<HistoricDataWrapper>();
                int resBusqueda = StaticTools.buscarIntervalo(lista, queryParameters.get(ValidacionesEstaticas.intervaloHistorico));
                if (resBusqueda != -1) {
                    resHistorico = Optional.ofNullable(lista.get(resBusqueda));
                } else {
                    RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + ValidacionesEstaticas.endPointHistoric + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class, activoMONGO.get());
                    if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                    resHistorico = Optional.ofNullable(obj.getData());
                }

            } else {
                RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + ValidacionesEstaticas.endPointHistoric + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                resHistorico = Optional.ofNullable(obj.getData());
            }
        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
        return resHistorico.get();
    }
    @ApiOperation(value = "Simple Moving Average", notes = "Get the simple moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/sma",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarSMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("sma",queryParameters);
    }

    @ApiOperation(value = "Exponential MA", notes = "Get the exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/ema",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("ema",queryParameters);
    }
    @ApiOperation(value = "Double Exponential MA", notes = "Get the double exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/dema",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarDEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("dema",queryParameters);
    }

    @ApiOperation(value = "Kaufman Adaptive MA", notes = "Get the kaufman's adaptive moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/kama",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarKAMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("kama",queryParameters);
    }

    @ApiOperation(value = "MESA Adaptive MA", notes = "Get the MESA adaptive moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/mama",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarMAMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("mama",queryParameters);
    }

    @ApiOperation(value = "Triple Exponential MA", notes = "Get the triple's exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/tema",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarTEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("tema",queryParameters);
    }

    @ApiOperation(value = "Triangular MA", notes = "Get the tringular's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/tma",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarTMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("tma",queryParameters);
    }

    @ApiOperation(value = "Weighted MA", notes = "Get the weighted's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Technichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/wma",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarWMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("wma",queryParameters);
    }

    @ApiOperation(value = "T3 MA", notes = "Get the t3's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value="/technical/t3",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarT3(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("t3",queryParameters);
    }

    @ApiOperation(value = "Relative Strength Index", notes = "Get the relative strength index")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/technical/rsi",params = {"base","counter","historicInterval","indicatorInterval","tipoSeriesIndicador"},produces = MediaType.APPLICATION_JSON_VALUE )
    public ArrayList<TechnicalRegistry> recuperarRSI(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("rsi",queryParameters);
    }



    private ArrayList<TechnicalRegistry> recuperarIndicadorAVG(String nombre, Map<String,String>queryParameters){
        if (ValidacionesEstaticas.validacionSMA(queryParameters)) {
            Optional<AssetPrice> precioMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase) + queryParameters.get(ValidacionesEstaticas.nombreParContra));
            if (precioMONGO.isPresent()) {
                int resBusqueda = StaticTools.buscarIndicador(precioMONGO.get().getIndicatorList(),
                        nombre.toLowerCase(),
                        Integer.parseInt(queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador)),
                        queryParameters.get(ValidacionesEstaticas.intervaloHistorico),
                        queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
                if (resBusqueda != -1) {
                    return precioMONGO.get().getIndicatorList().get(resBusqueda).getRawTechnicalData();
                } else {
                    RespuestaIndicadorTecnico obj = new RestTemplate().getForObject(StaticTools.getTechnicalIndicatorURL(nombre.toLowerCase(), queryParameters), RespuestaIndicadorTecnico.class);
                    if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                    return obj.getListaTecnico();
                }
            }else{
                RespuestaIndicadorTecnico obj = new RestTemplate().getForObject(StaticTools.getTechnicalIndicatorURL(nombre.toLowerCase(), queryParameters), RespuestaIndicadorTecnico.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                return obj.getListaTecnico();
            }

        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
    }


}
