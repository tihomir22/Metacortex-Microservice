package com.metacortex.api.controladores;

import com.metacortex.api.Excepciones.ActivoNoEncontradoException;
import com.metacortex.api.ModelosResultado.OnlyPrice;
import com.metacortex.api.Respuestas.RespuestaPersonalizada;
import com.metacortex.api.Respuestas.RespuestaPersonalizadaHistorico;
import com.metacortex.api.Validaciones.StaticTools;
import com.metacortex.api.Validaciones.ValidacionesEstaticas;
import com.metacortex.api.entidades.AssetPrice;
import com.metacortex.api.entidades.HistoricDataWrapper;
import com.metacortex.api.repositorio.RepositorioActivos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
@RequestMapping(path = "/data")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "Price Data Controller", tags = { "Price Data" })
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
                RespuestaPersonalizada obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + "prices/" + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra), RespuestaPersonalizada.class);
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
                    RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + "historic/" + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class, activoMONGO.get());
                    if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                    resHistorico = Optional.ofNullable(obj.getData());
                }

            } else {
                RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLBASE + "historic/" + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                resHistorico = Optional.ofNullable(obj.getData());
            }
        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
        return resHistorico.get();
    }








}
