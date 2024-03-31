package com.example.TradeHub.domain.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Schema(description = "The essence of the currency," +
                        "domain-specific fields were described separately")
@Table("currencies")
public class Currency {
    @Id
    public Long id;
    
    @Schema(description = "Contains the code that will be needed to get information using BinanceAPI")
    @Column(value = "base_asset")
    public String baseAsset;
}
