package com.hansol.bloomingspring.hexagonal.stock.domain.service;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface GetStockMarketPricePort {
    Mono<BigDecimal> get(String symbol);
}
