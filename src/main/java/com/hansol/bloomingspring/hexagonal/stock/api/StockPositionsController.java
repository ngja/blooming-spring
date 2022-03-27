package com.hansol.bloomingspring.hexagonal.stock.api;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import com.hansol.bloomingspring.hexagonal.stock.domain.service.GetStockPositionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class StockPositionsController {

    private final GetStockPositionService getStockPositionService;
    private final GetStockMarketValueService getStockMarketValueService;

    public StockPositionsController(GetStockPositionService getStockPositionService, GetStockMarketValueService getStockMarketValueService) {
        this.getStockPositionService = getStockPositionService;
        this.getStockMarketValueService = getStockMarketValueService;
    }

    @GetMapping(path = "/stock-position-market-value/{symbol}")
    Mono<GetStockPositionAndMarketValueApiResponseDto> getPositionAndMarketValue(
            @AuthenticationPrincipal Mono<Principal> principalMono,
            @PathVariable String symbol
    ) {
        return principalMono.flatMap(principal -> getStockPositionService.get(principal.getName(), symbol))
                .zipWhen(stockPosition -> getStockMarketValueService.get(symbol, stockPosition.getQuantity()),
                        (stockPosition, marketValue) -> new GetStockPositionAndMarketValueApiResponseDto(
                                stockPosition.getSymbol(),
                                stockPosition.getQuantity(),
                                stockPosition.getCurrencyCode(),
                                stockPosition.getCost(),
                                marketValue
                        ));
    }
}
