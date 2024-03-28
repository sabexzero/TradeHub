package com.example.TradeHub.domain.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.relational.core.mapping.Column;

@Schema(
        description = "The essence of the currency, " +
                "domain-specific fields were described separately"
)
public class Currency {
    public Long id;
    
    @Schema(
            description = "Contains the code that will be needed to get information using BinanceAPI"
    )
    @Column(
            value = "base_asset"
    )
    public String baseAsset;
}
