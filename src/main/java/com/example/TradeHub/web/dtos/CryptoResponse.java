package com.example.TradeHub.web.dtos;

import com.example.TradeHub.domain.specified.CompletedCryptoBuyRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author sabextech
 * @param baseAsset
 * Buy: indicates the currency (monetary) that we wanted to use as a payment
 * Sell: indicates the cryptocurrency that the user intended to sell
 * To trade: indicates the cryptocurrency that we wanted to give away in exchange
 * @param quoteAsset
 * Buy & Trade: indicates the cryptocurrency we wanted to receive
 * Sell: indicates the currency (monetary) that the user intended to receive in exchange for cryptocurrency
 * @param baseAmount
 * Buy & Trade: indicates the amount of cryptocurrency that we wanted to receive
 * Sell: indicates the amount of cryptocurrency that we wanted to sell
 * @param quoteAmount
 * Buy & Trading & Sale: Indicates the amount of currency or cryptocurrency that the user has received
 */
public record CryptoResponse(
        Long userId,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "Buy: indicates the currency (monetary) that we wanted to use as a payment\n" +
                "Sell: indicates the cryptocurrency that the user intended to sell\n" +
                "Trade: indicates the cryptocurrency that we wanted to give away in exchange")
        String baseAsset,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "Buy & Trade: indicates the cryptocurrency we wanted to receive\n" +
                "Sell: indicates the currency (monetary) that the user intended to receive in exchange for cryptocurrency")
        String quoteAsset,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "Buy & Trade: indicates the amount of cryptocurrency that we wanted to receive\n" +
                "Sell: indicates the amount of cryptocurrency that we wanted to sell")
        BigDecimal baseAmount,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "Buy & Trade & Sell: Indicates the amount of currency or cryptocurrency that the user has received")
        BigDecimal quoteAmount,
        
        Boolean isSuccessful
) {
        public CryptoResponse(CryptoRequest request, BigDecimal quoteAmount, boolean isSuccessful){
                this(request.userId(), request.baseAsset(),
                        request.quoteAsset(), request.amount(), quoteAmount, isSuccessful);
        }
        
        public CryptoResponse(CompletedCryptoBuyRequest request, boolean isSuccessful){
                this(request.getUserId(), request.getBaseAsset(),
                        request.getQuoteAsset(), request.getAmount(),
                        request.getAmount().multiply(request.getOneUnitPrice()), isSuccessful);
        }
}
