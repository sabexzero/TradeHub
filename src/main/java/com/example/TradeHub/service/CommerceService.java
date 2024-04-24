package com.example.TradeHub.service;

import com.example.TradeHub.domain.annotations.CryptoTransactionHistory.CryptoTransaction;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.cryptocurrency.BuyCryptoRequestRepository;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.domain.specified.CompletedCryptoBuyRequest;
import com.example.TradeHub.web.dtos.CryptoUserRequest;
import com.example.TradeHub.web.dtos.CryptoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

/**
 This service class handles buying and selling operations within the application.
 */

@Service
@RequiredArgsConstructor
public class CommerceService {
    private final WalletsService walletsService;
    private final BuyCryptoRequestRepository buyCryptoRequestRepository;
    
    private final CoinApiService coinApiService;
    
    /**
     * The purchase operation is the only one that has a confirmation operation.
     * This is done because, only from the purchase operation, work with the user's bank follows,
     * where he confirms the transfer, this web application does not provide such functionality,
     * so this happens only conditionally, as with the sale of cryptocurrency.
     */
    public CompletedCryptoBuyRequest handleCryptoBuyRequest(
            CryptoUserRequest request
    ) throws IOException {
        BigDecimal buyPrice = coinApiService.getCryptocurrencyPrice(request.baseAsset(), request.quoteAsset());
        
        return buyCryptoRequestRepository.save(
                CompletedCryptoBuyRequest.builder()
                        .amount(request.amount())
                        .baseAsset(request.baseAsset())
                        .quoteAsset(request.quoteAsset())
                        .userId(request.userId())
                        .oneUnitPrice(buyPrice)
                        .build());
    }
    
    @Transactional
    @CryptoTransaction
    public CryptoUserResponse confirmCryptoBuyRequest(
            Long requestId
    ) throws NoSuchElementException {
        //Allegedly received money from the user
        boolean userHaveMoney = new Random().nextBoolean();
        CompletedCryptoBuyRequest request = buyCryptoRequestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        
        if(userHaveMoney){
            try{
                walletsService.increaseUserBalance(request.getUserId(),request.getQuoteAsset(), request.getAmount());
                buyCryptoRequestRepository.delete(request);
                return new CryptoUserResponse(request, true);
            } catch (Exception ex){
                return new CryptoUserResponse(request, false);
            }
        }
        return new CryptoUserResponse(request, false);
    }
    
    @CryptoTransaction
    public CryptoUserResponse handleCryptoSellRequest(
            CryptoUserRequest request
    ) throws NoSuchElementException {

        try {
            walletsService.decreaseUserBalance(request.userId(), request.baseAsset(), request.amount());
            
            BigDecimal sellPrice = coinApiService.getCryptocurrencyPrice(request.baseAsset(), request.quoteAsset());
            return new CryptoUserResponse(request, sellPrice, true);
        } catch (Exception ex){
            return new CryptoUserResponse(request, new BigDecimal(-1), false);
        }
    }
}
