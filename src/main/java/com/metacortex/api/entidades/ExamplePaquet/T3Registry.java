package com.metacortex.api.entidades.ExamplePaquet;

import com.metacortex.api.entidades.TechnicalRegistry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description="T3 Moving Average Single Registry")

public class T3Registry {

        @ApiModelProperty(notes="Number of the record , similar to id",example = "837")
        private int numRegistry;
        @ApiModelProperty(notes="Extra information about the registry", example = "T3 Registry #834")
        private String extra;
        @ApiModelProperty(notes="Historic price attached to the technical indicator registry below",example = "10014")
        private double historicPrice;
        @ApiModelProperty(notes="Technical indicator registry",example = "10319.642178217822")
        private double technicalIndicator;
        @ApiModelProperty(notes="Date of the technical registry ", example = "1563700393")
        private Date date;


    public T3Registry(TechnicalRegistry techny) {
        this.numRegistry=techny.getNumRegistry();
        this.extra=techny.getExtra();
        this.historicPrice=techny.getHistoricPrice();
        this.technicalIndicator=techny.getTechnicalIndicator();
        this.date=techny.getDate();
    }

    public T3Registry() {
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
