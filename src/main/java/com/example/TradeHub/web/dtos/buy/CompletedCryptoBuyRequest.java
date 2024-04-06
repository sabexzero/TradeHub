package com.example.TradeHub.web.dtos.buy;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * @param baseAsset - Indicates the currency (monetary) that we want to use as payment
 * @param quoteAsset - Indicates the cryptocurrency that we want to receive
 * @param amountToBuy - Indicates the amount of cryptocurrency that we want to receive
 * @param oneUnitPrice - The price of the cryptocurrency in the currency that the user has chosen as payment
 */
@Table("buy_crypto_requests")
@Schema(description = "The essence of the purchase request that needs to be confirmed")
public record CompletedCryptoBuyRequest(
        @Id
        Long userId,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "indicates the cryptocurrency that we want to receive")
        @Column("base_asset")
        String baseAsset,
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "indicates the currency (monetary) that we want to use as payment")
        @Column("quote_asset")
        String quoteAsset,
        
        @Column("amount_to_buy")
        @Schema(description = "Indicates the amount of cryptocurrency that we want to receive")
        BigDecimal amountToBuy,
        
        @Column("one_unit_price")
        @Schema(description = "The price of the cryptocurrency in the currency that the user has chosen as payment")
        BigDecimal oneUnitPrice
) {}
