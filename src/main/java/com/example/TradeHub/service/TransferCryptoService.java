package com.example.TradeHub.service;

import com.example.TradeHub.domain.annotations.CryptoTransactionHistory.CryptoTransaction;
import com.example.TradeHub.web.dtos.CryptoTransferRequest;
import com.example.TradeHub.web.dtos.CryptoTransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransferCryptoService {
    private final WalletsService walletsService;
    
    @Transactional
    @CryptoTransaction
    public CryptoTransferResponse handleTransferRequest(
            CryptoTransferRequest request
    ){
        try{
            walletsService.decreaseUserBalance(request.senderId(),request.sentAsset(),request.sentAmount());
            
            walletsService.increaseUserBalance(request.recipientId(),request.sentAsset(),request.sentAmount());
            
            return new CryptoTransferResponse(request, true);
        } catch (Exception ex){
            return new CryptoTransferResponse(request,false);
        }
    }
    
}
