package com.hansol.bloomingspring.hexagonal.stock.mongo;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPositionDocument extends StockPosition {
    @Id
    private ObjectId id;
}
