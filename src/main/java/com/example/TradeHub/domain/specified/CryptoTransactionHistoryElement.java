package com.example.TradeHub.domain.specified;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("crypto_transactions_history")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoTransactionHistoryElement {
    @Id
    private Long id;
    
    @Column("user_id")
    private Long userId;
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi")
    @Column("base_asset")
    private String baseAsset;
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi")
    @Column("quote_asset")
    private String quoteAsset;
    
    @Column("transaction_type")
    private String transactionType;
    
    @Column("base_amount")
    private BigDecimal baseAmount;
    
    @Column("quote_amount")
    private BigDecimal quoteAmount;
}
