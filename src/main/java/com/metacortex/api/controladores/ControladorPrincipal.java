package com.metacortex.api.controladores;

import com.metacortex.api.Excepciones.ActivoNoEncontradoException;
import com.metacortex.api.ModelosResultado.SoloPrecio;
import com.metacortex.api.Respuestas.RespuestaIndicadorTecnico;
import com.metacortex.api.Respuestas.RespuestaPersonalizada;
import com.metacortex.api.Respuestas.RespuestaPersonalizadaHistorico;
import com.metacortex.api.Validaciones.StaticTools;
import com.metacortex.api.Validaciones.ValidacionesEstaticas;
import com.metacortex.api.entidades.*;
import com.metacortex.api.repositorio.RepositorioActivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ControladorPrincipal {

    @Autowired
    private RepositorioActivos repositorioActivos;


    @GetMapping("/")
    public List<PrecioActivo> getAll() {
        List<PrecioActivo> lista = this.repositorioActivos.findAll();
        return lista;
    }

    @GetMapping("/price/**")
    public Optional<SoloPrecio> getPrice(@RequestParam Map<String, String> queryParameters) {
        Optional<SoloPrecio> res = null;

        if (ValidacionesEstaticas.esPeticionDePrecio(queryParameters)) {
            Optional<PrecioActivo> precioMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase) + queryParameters.get(ValidacionesEstaticas.nombreParContra));
            if (precioMONGO.isPresent()) {
                res = Optional.of(new SoloPrecio(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase(), precioMONGO.get().getPrecio(), new Date().getTime()));
            } else {
                RespuestaPersonalizada obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLLOCAL + ValidacionesEstaticas.endPointPrecio + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra), RespuestaPersonalizada.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                res = Optional.of(new SoloPrecio(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase(), obj.getData().getPrecio(), new Date().getTime()));
            }
        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
        return res;
    }

    @GetMapping("/historic/**")
    public Optional<TipoDatoHistorico> getHistoric(@RequestParam Map<String, String> queryParameters) {
        Optional<TipoDatoHistorico> resHistorico = null;
        if (ValidacionesEstaticas.esPeticionHistoric(queryParameters)) {
            Optional<PrecioActivo> activoMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase).toUpperCase() + queryParameters.get(ValidacionesEstaticas.nombreParContra).toUpperCase());
            if (activoMONGO.isPresent()) {
                ArrayList<TipoDatoHistorico> lista = activoMONGO.get().getListaDatosHora();
                if (lista == null) lista = new ArrayList<TipoDatoHistorico>();
                int resBusqueda = StaticTools.buscarIntervalo(lista, queryParameters.get(ValidacionesEstaticas.intervaloHistorico));
                if (resBusqueda != -1) {
                    resHistorico = Optional.ofNullable(lista.get(resBusqueda));
                } else {
                    RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLLOCAL + ValidacionesEstaticas.endPointHistoric + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class, activoMONGO.get());
                    if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                    resHistorico = Optional.ofNullable(obj.getData());
                }

            } else {
                RespuestaPersonalizadaHistorico obj = new RestTemplate().getForObject(ValidacionesEstaticas.URLLOCAL + ValidacionesEstaticas.endPointHistoric + queryParameters.get(ValidacionesEstaticas.nombreParBase) + "/" + queryParameters.get(ValidacionesEstaticas.nombreParContra) + "/" + queryParameters.get(ValidacionesEstaticas.intervaloHistorico), RespuestaPersonalizadaHistorico.class);
                if (obj.getEstado() != 200) throw new ActivoNoEncontradoException(obj.getMensaje());
                resHistorico = Optional.ofNullable(obj.getData());
            }
        } else {
            throw new ActivoNoEncontradoException("Incorrect parameters were introduced!");
        }
        return resHistorico;
    }

    @GetMapping("/technical/sma/**")
    public ArrayList<RegistroTecnico> recuperarSMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("sma",queryParameters);
    }

    @GetMapping("/technical/ema/**")
    public ArrayList<RegistroTecnico> recuperarEMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("ema",queryParameters);
    }

    @GetMapping("/technical/dema/**")
    public ArrayList<RegistroTecnico> recuperarDEMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("dema",queryParameters);
    }

    @GetMapping("/technical/kama/**")
    public ArrayList<RegistroTecnico> recuperarKAMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("kama",queryParameters);
    }

    @GetMapping("/technical/mama/**")
    public ArrayList<RegistroTecnico> recuperarMAMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("mama",queryParameters);
    }

    @GetMapping("/technical/tema/**")
    public ArrayList<RegistroTecnico> recuperarTEMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("tema",queryParameters);
    }

    @GetMapping("/technical/tma/**")
    public ArrayList<RegistroTecnico> recuperarTMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("tma",queryParameters);
    }

    @GetMapping("/technical/wma/**")
    public ArrayList<RegistroTecnico> recuperarWMA(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("wma",queryParameters);
    }

    @GetMapping("/technical/t3/**")
    public ArrayList<RegistroTecnico> recuperarT3(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("t3",queryParameters);
    }

    @GetMapping("/technical/rsi/**")
    public ArrayList<RegistroTecnico> recuperarRSI(@RequestParam Map<String, String> queryParameters) {
        return this.recuperarIndicadorAVG("rsi",queryParameters);
    }



    private ArrayList<RegistroTecnico> recuperarIndicadorAVG(String nombre,Map<String,String>queryParameters){
        if (ValidacionesEstaticas.validacionSMA(queryParameters)) {
            Optional<PrecioActivo> precioMONGO = this.repositorioActivos.findById(queryParameters.get(ValidacionesEstaticas.nombreParBase) + queryParameters.get(ValidacionesEstaticas.nombreParContra));
            if (precioMONGO.isPresent()) {
                int resBusqueda = StaticTools.buscarIndicador(precioMONGO.get().getListadoIndicatores(),
                        nombre.toLowerCase(),
                        Integer.parseInt(queryParameters.get(ValidacionesEstaticas.intervaloPeriodoIndicador)),
                        queryParameters.get(ValidacionesEstaticas.intervaloHistorico),
                        queryParameters.get(ValidacionesEstaticas.tipoSeriesIndicador));
                if (resBusqueda != -1) {
                    return precioMONGO.get().getListadoIndicatores().get(resBusqueda).getDatosTecnicos();
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
