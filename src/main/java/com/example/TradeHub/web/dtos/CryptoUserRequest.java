package com.example.TradeHub.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author sabextech
 * @param baseAsset
 * Buy: Indicates the currency (monetary) that we want to use as payment
 * Sell: Indicates the cryptocurrency that the user intends to sell
 * Trade: Indicates the cryptocurrency that we want to give away in exchange
 *
 * @param quoteAsset
 * Buy & Trade: Indicates the cryptocurrency that we want to receive
 * Sell: Indicates the currency (monetary) that the user intends to receive in exchange for cryptocurrency
 *
 * @param amount
 * Buy & Trade: Indicates the amount of cryptocurrency that we want to receive
 * Sell: Indicates the amount of cryptocurrency that we want to sell
 */

@Schema(description = "The essence of the request")
public record CryptoUserRequest(
    Long userId,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "Buy: Indicates the cryptocurrency that we want to receive\n" +
            "Sell: Indicates the currency (monetary) that the user intends to receive in exchange for cryptocurrency\n" +
            "Trade: Indicates the cryptocurrency that we want to give away in exchange")
    String baseAsset,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "Buy & Trade: Indicates the cryptocurrency that we want to receive\n" +
            "Sell: Indicates the cryptocurrency that the user intends to sell")
    String quoteAsset,
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
            "Buy & Trade: Indicates the amount of cryptocurrency that we want to receive\n" +
            "Sell: Indicates the amount of cryptocurrency that we want to sell")
    BigDecimal amount
) {}
