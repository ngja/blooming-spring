package com.hansol.bloomingspring.hexagonal.stock.domain.model;

import com.github.javafaker.Faker;
import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;

import java.math.BigDecimal;

public class DomainModelFaker {
    private static final Faker faker = Faker.instance();

    public static StockPosition fakeStockPosition(String user, String symbol) {
        return new StockPosition(
                user,
                symbol,
                fakeQuantity(),
                faker.currency().code(),
                fakeAmount()
        );
    }

    public static BigDecimal fakeQuantity() {
        return BigDecimal.valueOf(faker.number().randomDouble(2, 0, 10000));
    }

    public static BigDecimal fakeAmount() {
        return BigDecimal.valueOf(faker.number().randomDouble(4, 0, 10000000));
    }

    public static String fakeUser() {
        return faker.name().username();
    }

    public static String fakeStockSymbol() {
        return faker.stock().nsdqSymbol();
    }
}
