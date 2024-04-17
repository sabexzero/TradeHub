package com.example.TradeHub.web.dtos;

import lombok.Builder;

import java.math.BigDecimal;


public record CryptoTransferResponse (
        Long senderId,
        Long recipientId,
        String sentAsset,
        BigDecimal sentAmount,
        Boolean isSuccessful
){
    public CryptoTransferResponse(
            CryptoTransferRequest request,
            boolean isSuccessful
    ){
        this(
                request.senderId(),
                request.recipientId(),
                request.sentAsset(),
                request.sentAmount(),
                isSuccessful);
    }
}
