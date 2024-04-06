package com.example.TradeHub.web.dtos.sell;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @param baseAsset - Indicates the cryptocurrency that the user intends to sell
 * @param quoteAsset - Indicates the currency (monetary) that the user intends to receive in exchange for cryptocurrency
 * @param amountToSell - Indicates the amount of cryptocurrency that we want to receive
 */

@Schema(description = "The essence of the raw sell request")
public record CryptoSellRequest(
    Long userId,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates the cryptocurrency that we want to sell")
    String baseAsset,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates the currency that we want to get in exchange for cryptocurrency")
    String quoteAsset,
    
    @Schema(description = "Indicates the amount of cryptocurrency that we want to sell")
    BigDecimal amountToSell
) {}