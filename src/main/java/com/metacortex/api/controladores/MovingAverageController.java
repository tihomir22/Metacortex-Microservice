package com.metacortex.api.controladores;

import com.metacortex.api.Validaciones.StaticTools;
import com.metacortex.api.entidades.AssetPrice;
import com.metacortex.api.entidades.ExamplePaquet.*;
import com.metacortex.api.entidades.TechnicalRegistry;
import com.metacortex.api.repositorio.RepositorioActivos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/technical/ma")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "Moving Average Controller", tags = {"Moving Average"})
public class MovingAverageController {

    @Autowired
    private RepositorioActivos repositorioActivos;

    @ApiIgnore
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, name = "Sambomba")
    public List<AssetPrice> getAll() {
        List<AssetPrice> lista = this.repositorioActivos.findAll();
        return lista;
    }

    @ApiOperation(value = "Simple Moving Average", notes = "Get the simple moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")
    })
    @GetMapping(value = "/sma", produces = MediaType.APPLICATION_JSON_VALUE)
    public SMARegistry[][] recuperarSMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toSMAExample(StaticTools.recuperarIndicador("sma", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Exponential MA", notes = "Get the exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })
    @GetMapping(value = "/ema", produces = MediaType.APPLICATION_JSON_VALUE)
    public EMARegistry[][] recuperarEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toEMAExample(StaticTools.recuperarIndicador("ema", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Double Exponential MA", notes = "Get the double exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })
    @GetMapping(value = "/dema", produces = MediaType.APPLICATION_JSON_VALUE)
    public DEMARegistry[][] recuperarDEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toDEMAExample(StaticTools.recuperarIndicador("dema", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Kaufman Adaptive MA", notes = "Get the kaufman's adaptive moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })
    @GetMapping(value = "/kama", produces = MediaType.APPLICATION_JSON_VALUE)
    public KAMARegistry[][] recuperarKAMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toKAMAExample(StaticTools.recuperarIndicador("kama", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "MESA Adaptive MA", notes = "Get the MESA adaptive moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })
    @GetMapping(value = "/mama", produces = MediaType.APPLICATION_JSON_VALUE)
    public MAMARegistry[][] recuperarMAMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toMAMAExample(StaticTools.recuperarIndicador("mama", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Triple Exponencial Moving Average", notes = "Get the triple's exponential moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })

    /* TODO  REVISAR POR QUÉ DEVUELVE -99999 a partir de X registros*/
    @GetMapping(value = "/tema", produces = MediaType.APPLICATION_JSON_VALUE)
    public TEMARegistry[][] recuperarTEMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toTEMARegistry(StaticTools.recuperarIndicador("tema", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Triangular Moving Average", notes = "Get the tringular's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),  @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")


    })

    @GetMapping(value = "/tma", produces = MediaType.APPLICATION_JSON_VALUE)
    public TMARegistry[][] recuperarTMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toTMARegistry(StaticTools.recuperarIndicador("tma", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "Weighted MA", notes = "Get the weighted's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Technichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })
    @GetMapping(value = "/wma", produces = MediaType.APPLICATION_JSON_VALUE)
    public WMARegistry[][] recuperarWMA(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toWMARegistry(StaticTools.recuperarIndicador("wma", queryParameters, this.repositorioActivos, "general"));
    }

    @ApiOperation(value = "T3 MA", notes = "Get the Telson's moving average")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "Starting period of the historic data in Unix Timestamp, such as 1467347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "endTime", value = "Ending period of the historic data in Unix Timestamp, such as 1497347635. Default 0 (disabled)", required = false, dataType = "string", paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "Limit of historical entries for calculating the indicator, [0-1000 Integers are accepted]. Default 1000", required = false, dataType = "int", paramType = "query",defaultValue = "1000")

    })

    /* TODO IGUAL QUE TEMA, MUESTRA -99999 SIN LLEGAR A MOSTRAR NINGUN REGISTRO, REVISAR*/
    @GetMapping(value = "/t3", produces = MediaType.APPLICATION_JSON_VALUE)
    public T3Registry[][] recuperarT3(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return Mapper.toT3Registry(StaticTools.recuperarIndicador("t3", queryParameters, this.repositorioActivos, "general"));
    }
}
