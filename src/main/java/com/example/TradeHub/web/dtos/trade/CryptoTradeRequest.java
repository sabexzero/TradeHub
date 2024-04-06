package com.example.TradeHub.web.dtos.trade;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @param baseAsset - Indicates the cryptocurrency that we want to give away in exchange
 * @param quoteAsset - Indicates the cryptocurrency that we want to receive in exchange
 * @param amountToGet  - How much does the user want to receive cryptocurrencies
 */

@Schema(description = "The essence of the raw trade request")
public record CryptoTradeRequest(
    Long userId,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates cryptocurrency that we want to give in exchange for another cryptocurrency")
    String baseAsset,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates cryptocurrency that we want to receive in exchange for another cryptocurrency")
    String quoteAsset,
    
    @Schema(description = "Indicates the amount of cryptocurrency that we want to receive")
    BigDecimal amountToGet
) { }
