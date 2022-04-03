package com.hansol.bloomingspring.hexagonal.stock.domain.service;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import reactor.core.publisher.Mono;

public interface StockPositionsRepository {
    Mono<StockPosition> findOneByUserAndSymbol(String user, String symbol);

    Mono<Void> deleteAll();

    Mono<StockPosition> insert(StockPosition stockPosition);
}
