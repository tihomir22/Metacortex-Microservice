package com.metacortex.api.entidades.ExamplePaquet;

import com.metacortex.api.entidades.TechnicalRegistry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description="Triangular Moving Average Single Registry")

public class TMARegistry {

        @ApiModelProperty(notes="Number of the record , similar to id",example = "737")
        private int numRegistry;
        @ApiModelProperty(notes="Extra information about the registry", example = "TRIMA Registry #737")
        private String extra;
        @ApiModelProperty(notes="Historic price attached to the technical indicator registry below",example = "10111")
        private double historicPrice;
        @ApiModelProperty(notes="Technical indicator registry",example = "10568.26")
        private double technicalIndicator;
        @ApiModelProperty(notes="Date of the technical registry ", example = "1563700393")
        private Date date;


    public TMARegistry(TechnicalRegistry techny) {
        this.numRegistry=techny.getNumRegistry();
        this.extra=techny.getExtra();
        this.historicPrice=techny.getHistoricPrice();
        this.technicalIndicator=techny.getTechnicalIndicator();
        this.date=techny.getDate();
    }

    public TMARegistry() {
    }

    @Override
    public String toString() {
        return "SMARegistry{" +
                "numRegistry=" + numRegistry +
                ", extra='" + extra + '\'' +
                ", historicPrice=" + historicPrice +
                ", technicalIndicator=" + technicalIndicator +
                ", date=" + date +
                '}';
    }

    public int getNumRegistry() {
        return numRegistry;
    }

    public void setNumRegistry(int numRegistry) {
        this.numRegistry = numRegistry;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public double getHistoricPrice() {
        return historicPrice;
    }

    public void setHistoricPrice(double historicPrice) {
        this.historicPrice = historicPrice;
    }

    public double getTechnicalIndicator() {
        return technicalIndicator;
    }

    public void setTechnicalIndicator(double technicalIndicator) {
        this.technicalIndicator = technicalIndicator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
