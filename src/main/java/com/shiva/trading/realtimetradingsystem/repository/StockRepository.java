package com.shiva.trading.realtimetradingsystem.repository;

import com.shiva.trading.realtimetradingsystem.model.Stockmodel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stockmodel, String> {
    List<Stockmodel> findByTicker(String ticker);
}
