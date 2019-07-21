package com.metacortex.api.ModelosResultado;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Model for the response of live price data.")
public class OnlyPrice {
    @ApiModelProperty(notes="Name of the asset",example = "BTCUSDT")
    private String asset;
    @ApiModelProperty(notes="Live price of the asset above",example = "10531,34")
    private double price;
    @ApiModelProperty(notes="Curremt timestamp of the request",example = "1563566865")
    private long timestamp;


    public OnlyPrice(String asset, double price, long timestamp) {
        this.asset = asset;
        this.price = price;
        this.timestamp = timestamp;
    }

    public OnlyPrice() {
    }

    @Override
    public String toString() {
        return "OnlyPrice{" +
                "asset='" + asset + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
