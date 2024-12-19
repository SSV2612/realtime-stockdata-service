package com.shiva.trading.realtimetradingsystem.controller;

import com.shiva.trading.realtimetradingsystem.DTO.StockDataDTO;
import com.shiva.trading.realtimetradingsystem.service.RealStockDataService;
import com.shiva.trading.realtimetradingsystem.service.RealStockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class RealTimeStockFetchController {

    @Autowired
    private RealStockDataService realStockDataService;


    /*
    public String getPriceTicker(@PathVariable String ticker) {
        return realrealtimetradingsystem.getRealStockPrice(ticker);
    }
    */

    @GetMapping("/price/{ticker}")
    public StockDataDTO getPriceTicker(@PathVariable String ticker){
        StockDataDTO stockdataDTO = realStockDataService.getRealStockData(ticker);

        if (stockdataDTO != null) {
            realStockDataService.saveStockToDatabase(stockdataDTO);
        }

        return stockdataDTO;
    }
}
