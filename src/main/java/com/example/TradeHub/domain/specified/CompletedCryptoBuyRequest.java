package com.example.TradeHub.domain.specified;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * @author sabextech
 *  baseAsset - Indicates the currency (monetary) that we want to use as payment
 *  quoteAsset - Indicates the cryptocurrency that we want to receive
 *  amount - Indicates the amount of cryptocurrency that we want to receive
 *  oneUnitPrice - The price of the cryptocurrency in the currency that the user has chosen as payment
 */
@Table("buy_crypto_requests")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "The essence of the purchase request that needs to be confirmed")
public class CompletedCryptoBuyRequest {
        @Id
        private Long id;
        
        private Long userId;
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "indicates the cryptocurrency that we want to receive")
        @Column("base_asset")
        private String baseAsset;
        
        @Schema(description = "Contains the code that will be needed to get information using NinjasApi, " +
                "indicates the currency (monetary) that we want to use as payment")
        @Column("quote_asset")
        private String quoteAsset;
        
        @Column("amount_to_buy")
        @Schema(description = "Indicates the amount of cryptocurrency that we want to receive")
        private BigDecimal amount;
        
        @Column("one_unit_price")
        @Schema(description = "The price of the cryptocurrency in the currency that the user has chosen as payment")
        private BigDecimal oneUnitPrice;

}
