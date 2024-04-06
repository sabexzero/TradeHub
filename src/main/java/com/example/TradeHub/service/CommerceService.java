package com.example.TradeHub.service;

import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.cryptocurrency.BuyCryptoRequestRepository;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.web.dtos.buy.CompletedCryptoBuyRequest;
import com.example.TradeHub.web.dtos.sell.CompletedCryptoSellRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 This service class handles buying and selling operations within the application.
 */

@Service
@RequiredArgsConstructor
public class CommerceService {
    private final CryptoWalletRepository cryptoWalletRepository;
    private final BuyCryptoRequestRepository buyCryptoRequestRepository;
    
    /**
     * The purchase operation is the only one that has a confirmation operation.
     * This is done because, only from the purchase operation, work with the user's bank follows,
     * where he confirms the transfer, this web application does not provide such functionality,
     * so this happens only conditionally, as with the sale of cryptocurrency.
     */
    public CompletedCryptoBuyRequest handleCryptoBuyRequest(
            CompletedCryptoBuyRequest request
    ){
        return buyCryptoRequestRepository.save(new CompletedCryptoBuyRequest(
                request.userId(),
                request.baseAsset(),
                request.quoteAsset(),
                request.amountToBuy(),
                request.oneUnitPrice()
        ));
    }
    
    public void confirmCryptoBuyRequest(
            Long requestId
    ){
        //Allegedly received money from the user
        
        CompletedCryptoBuyRequest request = buyCryptoRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        Optional<CryptoWallet> userWallet = cryptoWalletRepository.findByUserAndCryptocurrency(
                request.userId(), request.baseAsset());
        
        if(userWallet.isPresent()){
            CryptoWallet checkedWallet = userWallet.get();
            checkedWallet.IncreaseBalance(request.amountToBuy());
            cryptoWalletRepository.save(checkedWallet);
        } else {
            CryptoWallet cryptoWallet = CryptoWallet.builder()
                    .balance(request.amountToBuy())
                    .base_asset(request.baseAsset())
                    .userId(request.userId())
                    .build();
        }
    }
    
    public void handleCryptoSellRequest(
            CompletedCryptoSellRequest request
    ){
        
        CryptoWallet userWallet = cryptoWalletRepository.findByUserAndCryptocurrency(request.userId(), request.baseAsset())
                .orElseThrow(() -> new RuntimeException("The person does not have a wallet with this cryptocurrency"));
        
        int changeBalanceResult = userWallet.DecreaseBalance(request.amountToSell());
        
        if(changeBalanceResult >= 0){
            if(changeBalanceResult == 1)
                cryptoWalletRepository.save(userWallet);
            else
                cryptoWalletRepository.delete(userWallet);
        }
        else
            throw new RuntimeException("There are not enough funds in the wallet to sell cryptocurrencies");
    }
}
