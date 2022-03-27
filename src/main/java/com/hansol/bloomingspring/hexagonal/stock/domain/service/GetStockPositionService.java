package com.hansol.bloomingspring.hexagonal.stock.domain.service;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetStockPositionService {
    // should have user parameter
    public Mono<StockPosition> get(
            String user,
            String symbol
    ) {
        return Mono.empty();
    }
}
