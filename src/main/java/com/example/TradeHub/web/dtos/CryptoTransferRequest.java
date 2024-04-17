package com.example.TradeHub.web.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("crypto_transfer_requests")
public record CryptoTransferRequest(
        @Id
        Long id,
        
        @Column("sender_id")
        Long senderId,
        
        @Column("recipient_id")
        Long recipientId,
        
        @Column("send_asset")
        String sentAsset,
        
        @Column("sent_amount")
        BigDecimal sentAmount
){}
