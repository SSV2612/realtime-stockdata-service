package com.shiva.trading.realtimetradingsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shiva.trading.realtimetradingsystem.DTO.StockDataDTO;
import com.shiva.trading.realtimetradingsystem.exception.StockNotFoundException;
import com.shiva.trading.realtimetradingsystem.repository.StockRepository;
import com.shiva.trading.realtimetradingsystem.model.Stockmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RealStockDataService {

    private final String API_KEY = "HWQ0O4W9OMHM7CW6";
    private final String BASE_URL = "https://www.alphavantage.co/query";

    @Autowired
    private StockRepository stockRepository;

    public String getRealStockPrice(String ticker){
        String url = String.format("%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", BASE_URL, ticker, API_KEY);
        RestTemplate restTemplate = new RestTemplate();

        try{
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return jsonResponse;
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error fetching data for ticker:" + ticker;
        }
    }

    @Cacheable(value="stockPrice", key="#ticker", unless = "#result==null")
    public StockDataDTO getRealStockData(String ticker){
        String url = String.format("%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", BASE_URL, ticker, API_KEY);
        RestTemplate restTemplate = new RestTemplate();

        try{
            String jsonResponse = restTemplate.getForObject(url, String.class);
            System.out.println("Raw API response:" + jsonResponse);

            // Parse response for important fields
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode globalQuote = rootNode.path("Global Quote");

            if (globalQuote.isMissingNode() || globalQuote.path("05. price").asText().isEmpty()) {
                throw new StockNotFoundException("Stock data not found for ticker: " + ticker);
            }

            String price = globalQuote.path("05. price").asText();
            String lastUpdated = globalQuote.path("07. latest trading day").asText();
            String volume = globalQuote.path("06. volume").asText();

            return new StockDataDTO(ticker, Double.parseDouble(price), lastUpdated, Long.parseLong(volume));
        } catch (StockNotFoundException e) {
            throw e; // Custom exception is rethrown
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching data from API: " + e.getMessage());
        }
    }

    public void saveStockToDatabase(StockDataDTO stockDataDTO) {
        try {
            // Convert lastUpdated from String to Date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date lastUpdatedDate = formatter.parse(stockDataDTO.getLastUpdated());

            // Map DTO to Model
            Stockmodel model = new Stockmodel(
                    stockDataDTO.getTicker(),
                    stockDataDTO.getPrice(),
                    stockDataDTO.getVolume(),
                    lastUpdatedDate
            );

            // Save to MongoDB
            stockRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error saving stock data to database: " + e.getMessage());
        }
    }
}
