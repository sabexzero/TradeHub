package com.example.TradeHub.domain.specified;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("crypto_transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoTransaction {
    @Id
    private Long id;
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi")
    @Column("base_asset")
    private String baseAsset;
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi")
    @Column("quote_asset")
    private String quoteAsset;
    
    private TransactionType transactionType;
    
    @Column("base_amount")
    private BigDecimal baseAmount;
    
    @Column("quote_amount")
    private BigDecimal quoteAmount;
}
