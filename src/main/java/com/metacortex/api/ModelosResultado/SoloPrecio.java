package com.metacortex.api.ModelosResultado;

public class SoloPrecio {
    private String asset;
    private double price;
    private long timestamp;


    public SoloPrecio(String asset, double price, long timestamp) {
        this.asset = asset;
        this.price = price;
        this.timestamp = timestamp;
    }

    public SoloPrecio() {
    }

    @Override
    public String toString() {
        return "SoloPrecio{" +
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
