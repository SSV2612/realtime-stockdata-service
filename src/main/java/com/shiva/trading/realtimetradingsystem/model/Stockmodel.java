package com.shiva.trading.realtimetradingsystem.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "stocks")
public class Stockmodel {

    @Id
    private String id;

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    @Min(value=0, message = "Price must be positive")
    private Double price;

    private Long volume;
    private Date lastUpdated;

    // Default Constructor
    public Stockmodel() {}

    public Stockmodel(String ticker, Double price, Long volume, Date lastUpdated) {
        this.ticker = ticker;
        this.price = price;
        this.volume = volume;
        this.lastUpdated = lastUpdated;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public Long getVolume() {
        return volume;
    }
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
