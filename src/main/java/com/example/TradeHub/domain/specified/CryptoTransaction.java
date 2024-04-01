package com.example.TradeHub.domain.specified;

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
    
    @Column("base_asset")
    private String baseAsset;
    
    @Column("quote_asset")
    private String quoteAsset;
    
    private TransactionType transactionType;
    
    @Column("base_amount")
    private BigDecimal baseAmount;
    
    @Column("quote_amount")
    private BigDecimal quoteAmount;
}
