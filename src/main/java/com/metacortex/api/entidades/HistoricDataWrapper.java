package com.metacortex.api.entidades;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

@ApiModel(description="Wrapper for the historic data")
public class HistoricDataWrapper {

    @ApiModelProperty(notes="Period of the historic data",example = "1h")
    private String period;

    @ApiModelProperty(notes="Number of historic entries returned",example = "500")
    private int numRecords;

    @ApiModelProperty(notes="List of Historic Data objects")
    private ArrayList<HistoricData> rawHistoricData;

    @ApiModelProperty(notes="Unix timestamp which sets the beginning of the the desired historic data range",example = "1483243199000")
    private String startTime;

    @ApiModelProperty(notes="Unix timestamp which sets the end of the the desired historic data range",example = "1493245199000")
    private String endTime;

    @ApiModelProperty(notes = "Length of historic data entries. Maximum 1000.",example = "700")
    private int limit;

    public HistoricDataWrapper(String period, ArrayList<HistoricData> rawHistoricData) {
        this.period = period;
        this.rawHistoricData = rawHistoricData;
    }

    public HistoricDataWrapper() {
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getNumRecords() {
        return numRecords;
    }

    public void setNumRecords(int numRecords) {
        this.numRecords = numRecords;
    }

    public ArrayList<HistoricData> getRawHistoricData() {
        return rawHistoricData;
    }

    public void setRawHistoricData(ArrayList<HistoricData> rawHistoricData) {
        this.rawHistoricData = rawHistoricData;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}