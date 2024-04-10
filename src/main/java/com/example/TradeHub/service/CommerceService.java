package com.example.TradeHub.service;

import com.example.TradeHub.domain.annotations.CryptoTransactionHistory.CryptoTransaction;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.cryptocurrency.BuyCryptoRequestRepository;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.domain.specified.CompletedCryptoBuyRequest;
import com.example.TradeHub.web.dtos.CryptoRequest;
import com.example.TradeHub.web.dtos.CryptoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

/**
 This service class handles buying and selling operations within the application.
 */

@Service
@RequiredArgsConstructor
public class CommerceService {
    private final CryptoWalletRepository cryptoWalletRepository;
    private final BuyCryptoRequestRepository buyCryptoRequestRepository;
    
    private final CoinApiService coinApiService;
    
    /**
     * The purchase operation is the only one that has a confirmation operation.
     * This is done because, only from the purchase operation, work with the user's bank follows,
     * where he confirms the transfer, this web application does not provide such functionality,
     * so this happens only conditionally, as with the sale of cryptocurrency.
     */
    public CompletedCryptoBuyRequest handleCryptoBuyRequest(
            CryptoRequest request
    ){
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
    public CryptoResponse confirmCryptoBuyRequest(
            Long requestId
    ) throws RuntimeException {
        
        //Allegedly received money from the user
        boolean userHaveMoney = new Random().nextBoolean();
        CompletedCryptoBuyRequest request = buyCryptoRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        if(userHaveMoney){
            
            Optional<CryptoWallet> userWallet = cryptoWalletRepository.findByUserAndCryptocurrency(
                    request.getUserId(), request.getQuoteAsset());
            
            if(userWallet.isPresent()){
                CryptoWallet existCryptoWallet = userWallet.get();
                existCryptoWallet.IncreaseBalance(request.getAmount());
                cryptoWalletRepository.save(existCryptoWallet);
            } else {
                CryptoWallet newCryptoWallet = CryptoWallet.builder()
                        .balance(request.getAmount())
                        .base_asset(request.getQuoteAsset())
                        .userId(request.getUserId())
                        .build();
                cryptoWalletRepository.save(newCryptoWallet);
            }
            buyCryptoRequestRepository.delete(request);
        }
        return new CryptoResponse(request, userHaveMoney);
    }
    
    @CryptoTransaction
    public CryptoResponse handleCryptoSellRequest(
            CryptoRequest request
    ) throws RuntimeException {
        
        boolean isSuccessful = false;
        
        CryptoWallet userWallet = cryptoWalletRepository.findByUserAndCryptocurrency(request.userId(), request.baseAsset())
                .orElseThrow(() -> new RuntimeException("The person does not have a wallet with this cryptocurrency"));
        
        int changeBalanceResult = userWallet.DecreaseBalance(request.amount());
        
        if(changeBalanceResult >= 0) {
            if (changeBalanceResult == 1) {
                cryptoWalletRepository.save(userWallet);
            } else {
                cryptoWalletRepository.delete(userWallet);
            }
            BigDecimal sellPrice = coinApiService.getCryptocurrencyPrice(request.baseAsset(), request.quoteAsset());
            isSuccessful = true;
            return new CryptoResponse(request, sellPrice, isSuccessful);
        }
        return new CryptoResponse(request, new BigDecimal(changeBalanceResult), isSuccessful);
    }
}
