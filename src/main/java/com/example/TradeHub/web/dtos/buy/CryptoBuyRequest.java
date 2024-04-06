package com.example.TradeHub.web.dtos.buy;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @param baseAsset - Indicates the currency (monetary) that we want to use as payment
 * @param quoteAsset - Indicates the cryptocurrency that we want to receive
 * @param amountToBuy - Indicates the amount of cryptocurrency that we want to receive
 */

@Schema(description = "The essence of the raw purchase request")
public record CryptoBuyRequest(
    Long userId,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates the cryptocurrency that we want to receive")
    String baseAsset,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "indicates the currency (monetary) that we want to use as payment")
    String quoteAsset,
    
    @Schema(description = "Indicates the amount of cryptocurrency that we want to receive")
    
    BigDecimal amountToBuy
) {}
