package com.shiva.trading.realtimetradingsystem.DTO;

import java.io.Serializable;

public class StockDataDTO implements Serializable {

    private String ticker;
    private Double price;
    private String lastUpdated;
    private Long volume;

    public StockDataDTO(String ticker, Double price, String lastUpdated, Long volume) {
        this.ticker = ticker;
        this.price = price;
        this.lastUpdated = lastUpdated;
        this.volume = volume;
    }
    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getVolume() {
        return volume;
    }
    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
