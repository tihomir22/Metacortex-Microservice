package com.metacortex.api.controladores;

import com.metacortex.api.Validaciones.MetacortexStaticLibrary.StaticTools;
import com.metacortex.api.entidades.TechnicalRegistry;
import com.metacortex.api.repositorio.RepositorioActivos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/technical/oscillators")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "Oscillators Controller", tags = { "Oscillators" })
public class OscillatorController {


    @Autowired
    private RepositorioActivos repositorioActivos;

    @ApiOperation(value = "Relative Strength Index", notes = "Get the relative strength index")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/RSI",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarRSI(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("rsi",queryParameters,this.repositorioActivos,"general");
    }

    @ApiOperation(value = "Stochastic oscillator", notes = "The calculation above finds the range between an asset’s high and low price during a given period of time. The current security's price is then expressed as a percentage of this range with 0% indicating the bottom of the range and 100% indicating the upper limits of the range over the time period covered. The idea behind this indicator is that prices tend to close near the extremes of the recent range before turning points")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name ="fKperiod", value = "The time frame period of the fast k moving average. Positive integers are accepted [1,5,10,20...]. By default, fKperiod=5.", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sKperiod", value = "The time frame period of the slow k moving average. Positive integers are accepted [1,5,10,20...]. By default, sKperiod=3.", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "kMaType", value = "The moving average type applied for the slowK moving average. We use integers to define the types. [ 0 => SMA , 1 => DEMA , 2 => EMA , 3 => KAMA , 4 => MAMA, 5 => T3 , 6 => TEMA , 7 => TRIMA , 8 => WMA]. By default 0 => Simple Moving Average", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sDperiod", value = "The time frame period of the slow d moving average. Positive integers are accepted [1,5,10,20...]. By default, sDperiod=3.", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "dMaType", value = "The moving average type applied for the slowD moving average. We use integers to define the types. [ 0 => SMA , 1 => DEMA , 2 => EMA , 3 => KAMA , 4 => MAMA, 5 => T3 , 6 => TEMA , 7 => TRIMA , 8 => WMA]. By default 0 => Simple Moving Average", required = false, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/STOCHASTIC",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarSTOCHASTIC(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("stochastic",queryParameters,this.repositorioActivos,"simpleSinSeries");
    }

    @ApiOperation(value="Moving Average Convergence/Divergence, MACD",notes = "Moving Average Convergence/Divergence (MACD) is a trend-following dynamic indicator. It indicates the correlation between two Moving Averages of a price.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),

            @ApiImplicitParam(name ="fastPeriod", value = "The time frame period of the fast period moving average. Positive integers are accepted [1,5,10,20...]. By default, fastPeriod=12.", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "slowPeriod", value = "The time frame period of the slow period moving average. Positive integers are accepted [1,5,10,20...]. By default, slowPeriod=26.", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "signalPeriod", value = "The time frame period of the signal period moving average. Positive integers are accepted [1,5,10,20...]. By default, signalPeriod=9.", required = false, dataType = "string", paramType = "query"),


    })
    @GetMapping(value = "/MACD",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarMACD(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("MACD",queryParameters,this.repositorioActivos,"simpleConSeries");
    }

    @ApiOperation(value="Commodity Channel Index",notes = "Commodity Channel Index Technical Indicator (CCI) measures the deviation of the commodity price from its average statistical price. High values of the index point out that the price is unusually high being compared with the average one, and low values show that the price is too low. In spite of its name, the Commodity Channel Index can be applied for any financial instrument, and not only for the wares.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),

    })
    @GetMapping(value = "/CCI",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarCCI(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("CCI",queryParameters,this.repositorioActivos,"simpleSinSeriesConII");
    }

    @ApiOperation(value="Aroon Indicator",notes = "Knowing when a market is trending is very useful, mainly because trend following technical analysis indicators can be profitable during trending markets but may cause losses during non-directional markets. Similarly, oscillators can be profitable indicators during range-bound markets, but might perform very poorly during strong trending markets. The Aroon indicator can help indicate which mode the market is in.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),

    })
    @GetMapping(value = "/AROON",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarAROON(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("AROON",queryParameters,this.repositorioActivos,"simpleSinSeriesConII");
    }

    @ApiOperation(value="Acumulation Distribution Chaikin",notes = "The Chaikin Oscillator is, at its core, an indicator of an indicator. The Chaikin Oscillator takes Accumulation/Distribution (ADL) and applies two Exponential Moving Averages of varying length to the line. The Chaikin Oscillator's value is then derived by subtracting the longer term EMA of the ADL from the shorter term EMA of the ADL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),

    })
    @GetMapping(value = "/AD",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarCHAIKIN(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("AD",queryParameters,this.repositorioActivos,"simpleSinSeries");
    }






}
