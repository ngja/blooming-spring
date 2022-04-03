package com.hansol.bloomingspring.hexagonal.stock.api;

import com.github.javafaker.Faker;
import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import com.hansol.bloomingspring.hexagonal.stock.domain.service.GetStockMarketValueService;
import com.hansol.bloomingspring.hexagonal.stock.domain.service.GetStockPositionService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebFluxTest(StockPositionsController.class)
public class GetStockPositionAndMarketValueApiTest {

    @Autowired
    private WebTestClient client;

    // Domain Service
    @MockBean
    private GetStockPositionService getStockPositionService;

    @MockBean
    private GetStockMarketValueService getStockMarketValueService;

    private static final Faker faker = Faker.instance();

    @Test
    @WithMockUser("peterpan")
    void get() {
        // arrange
        String symbol = "aapl";
        String user = "peterpan";
        StockPosition fakeStockPosition = getFakerStockPosition(user, symbol);
        when(getStockPositionService.get(user, symbol)).thenReturn(Mono.just(fakeStockPosition));
        BigDecimal fakeMarketPrice = BigDecimal.valueOf(faker.number().randomDouble(4, 0, 1000000));
        when(getStockMarketValueService.get(symbol, fakeStockPosition.getQuantity())).thenReturn(Mono.just(fakeMarketPrice));

        // act
        makeGetRequest(symbol)
                // assert
                .expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertAll(
                        () -> assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () -> assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(), Offset.offset(0.01)),
                        () -> assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () -> assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001)),
                        () -> assertThat(dto.getMarketValue().doubleValue()).isCloseTo(fakeMarketPrice.doubleValue(), Offset.offset(0.0001))
                ));
    }

    private WebTestClient.ResponseSpec makeGetRequest(String symbol) {
        return client.get().uri("/stock-position-market-value/" + symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Test
    @WithMockUser("peterpan")
    void emptyPosition() {
        String symbol = "aapl";
        when(getStockPositionService.get("peterpan", symbol)).thenReturn(Mono.empty());
        when(getStockMarketValueService.get(eq(symbol), any()))
                .thenReturn(Mono.just(BigDecimal.valueOf(faker.number().randomDouble(4, 0, 1000000))));
        makeGetRequest("aapl")
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    @WithAnonymousUser
    void anonymousGet() {
        makeGetRequest("aapl")
                .expectStatus().isUnauthorized();
    }

    @Test
    void unauthenticatedGet() {
        makeGetRequest("aapl")
                .expectStatus().isUnauthorized();
    }

    private StockPosition getFakerStockPosition(String user, String symbol) {
        return new StockPosition(
                user,
                symbol,
                BigDecimal.valueOf(faker.number().randomDouble(2, 0, 10000)),
                faker.currency().code(),
                BigDecimal.valueOf(faker.number().randomDouble(4, 0, 1000000))
        );
    }
}
