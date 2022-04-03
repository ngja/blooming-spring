package com.hansol.bloomingspring.hexagonal.stock.domain.service;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.DomainModelFaker;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetStockMarketValueServiceTest {

    private final GetStockMarketPricePort getStockMargetPricePort = mock(GetStockMarketPricePort.class);
    private final GetStockMarketValueService subject = new GetStockMarketValueService(getStockMargetPricePort);

    @Test
    void get() {
        // arrange
        String symbol = DomainModelFaker.fakeStockSymbol();
        BigDecimal fakeQuantity = DomainModelFaker.fakeQuantity();
        BigDecimal fakePrice = DomainModelFaker.fakeAmount();
        when(getStockMargetPricePort.get(symbol)).thenReturn(Mono.just(fakePrice));

        // act
        Mono<BigDecimal> result = subject.get(symbol, fakeQuantity);

        // assert
        StepVerifier.create(result)
                .expectNextMatches(amount -> amount.equals(fakeQuantity.multiply(fakePrice)))
                .verifyComplete();
    }
}