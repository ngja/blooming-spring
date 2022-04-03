package com.hansol.bloomingspring.hexagonal.stock.mongo;

import com.hansol.bloomingspring.hexagonal.stock.domain.service.StockPositionsRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongoStockPositionRepository extends StockPositionsRepository, ReactiveMongoRepository<StockPositionDocument, ObjectId> {
}
