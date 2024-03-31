package com.example.TradeHub.domain.cryptocurrency;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Schema(description = "The essence of the cryptocurrency, " +
                        "domain-specific fields were described separately")
@Table("cryptocurrencies")
public class Cryptocurrency {
    @Id
    public Long id;
    
    @Schema(description = "Contains the code that will be needed to get information using BinanceAPI")
    @Column(value = "base_asset")
    public String baseAsset;
    
    @Column(
            value = "base_asset"
    )
    public Long walletId;
}
