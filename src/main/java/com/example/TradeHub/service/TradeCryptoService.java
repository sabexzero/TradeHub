package com.example.TradeHub.service;

import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.web.dtos.trade.CompletedCryptoTradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeCryptoService {
    private final CryptoWalletRepository cryptoWalletRepository;
    
    //TODO: Simplify or decompose the method
    // labels: refactoring
    @Transactional
    public void handleTradeRequest(
            CompletedCryptoTradeRequest request
    ){
        //We receive a wallet from which the cryptocurrency will be debited
        CryptoWallet walletToGive = cryptoWalletRepository.findByUserAndCryptocurrency(
                request.userId(),
                request.baseAsset()
        ).orElseThrow(() -> new RuntimeException("The user does not have the necessary wallet"));
        
        //We receive a wallet to which the cryptocurrency will be credited
        Optional<CryptoWallet> walletToGet = cryptoWalletRepository.findByUserAndCryptocurrency(
                request.userId(),
                request.baseAsset()
        );
        
        //We are trying to write off the cryptocurrency
        BigDecimal withdrawn = request.amountToGet().multiply(request.receivedCryptoPrice());
        
        int changeBalanceResult = walletToGive.DecreaseBalance(withdrawn);
        
        //We react to the result of the operation
        if(changeBalanceResult >= 0){
            if(changeBalanceResult == 1)
                cryptoWalletRepository.save(walletToGive);
            else
                cryptoWalletRepository.delete(walletToGive);
        }
        else
            throw new RuntimeException("There are not enough funds in the wallet to sell cryptocurrencies");
        
        //Adding cryptocurrencies to the target wallet
        if(walletToGet.isPresent()){
            CryptoWallet checkedWallet = walletToGet.get();
            checkedWallet.IncreaseBalance(request.amountToGet());
            
            cryptoWalletRepository.save(checkedWallet);
        } else {
            CryptoWallet cryptoWallet = CryptoWallet.builder()
                    .balance(request.amountToGet())
                    .base_asset(request.baseAsset())
                    .userId(request.userId())
                    .build();
            cryptoWalletRepository.save(cryptoWallet);
        }
    }
}
