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
@RequestMapping(path = "/technical/trend")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "Trend Indicator Controller", tags = { "Trend" })
public class TrendIndicatorController {

    @Autowired
    private RepositorioActivos repositorioActivos;

    @ApiOperation(value="Average Directional Movement Index, ADX",notes = "Average Directional Movement Index Technical Indicator (ADX) helps to determine if there is a price trend.The point of extremum is used then as the market entry level. Thus, after the signal to buy (+DI is higher than -DI) one must wait till the price has exceeded the point of extremum, and only then buy. However, if the price fails to exceed the level of the point of extremum, one should retain the short position.The point of extremum is used then as the market entry level. Thus, after the signal to buy (+DI is higher than -DI) one must wait till the price has exceeded the point of extremum, and only then buy. However, if the price fails to exceed the level of the point of extremum, one should retain the short position.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),

    })
    @GetMapping(value = "/ADX",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarADX(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("ADX",queryParameters,this.repositorioActivos,"simpleSinSeriesConII");
    }

    @ApiOperation(value="On-Balance Volume, OBV",notes = "On Balance Volume (OBV) measures buying and selling pressure as a cumulative indicator that adds volume on up days and subtracts volume on down days. When the security closes higher than the previous close, all of the day’s volume is considered up-volume. When the security closes lower than the previous close, all of the day’s volume is considered down-volume.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/OBV",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarOBV(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("OBV",queryParameters,this.repositorioActivos,"simpleSinSeries");
    }


}
