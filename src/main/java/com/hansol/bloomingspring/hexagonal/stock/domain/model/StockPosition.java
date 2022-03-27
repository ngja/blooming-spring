package com.hansol.bloomingspring.hexagonal.stock.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StockPosition {
    private String symbol;
    private BigDecimal quantity;
    private String currencyCode;
    private BigDecimal cost;
}