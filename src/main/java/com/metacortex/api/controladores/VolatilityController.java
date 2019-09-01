package com.metacortex.api.controladores;


import com.metacortex.api.Validaciones.StaticTools;
import com.metacortex.api.entidades.TechnicalRegistry;
import com.metacortex.api.repositorio.RepositorioActivos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/technical/volatility")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "Volatility Controller", tags = { "Volatility" })
public class VolatilityController {

    @Autowired
    private RepositorioActivos repositorioActivos;

    @ApiOperation(value = "Bollinger bands", notes = "Bollinger Bands shows the levels of different highs and lows that a security price has reached in a particular duration and also its relative strength, where highs are near to the upper line and lows are near to lower line")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "base", value = "Base asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "counter", value = "Counter asset", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "historicInterval", value = "Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "indicatorInterval", value = "Tecnichal's Indicator Interval [10,20,50,100,200,500,...]", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "seriesType", value = "Price's series type [open,close,high,low]", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/bbands",produces = MediaType.APPLICATION_JSON_VALUE )
    public TechnicalRegistry[][] recuperarBBANDS(@ApiParam(hidden = true) @RequestParam Map<String, String> queryParameters) {
        return StaticTools.recuperarIndicador("rsi",queryParameters,this.repositorioActivos,"general");
    }
}
