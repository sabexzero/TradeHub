package com.example.TradeHub.service;

import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletsService {
    private final CryptoWalletRepository cryptoWalletRepository;
    
    public void increaseUserBalance(Long userId, String asset, BigDecimal amount){
        Optional<CryptoWallet> wallet = cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset);
        
        if(wallet.isEmpty()){
            cryptoWalletRepository.save(CryptoWallet.builder()
                    .balance(amount)
                    .baseAsset(asset)
                    .userId(userId)
                    .build()
            );
        } else {
            CryptoWallet existWallet = wallet.get();
            
            existWallet.IncreaseBalance(amount);
            
            cryptoWalletRepository.save(existWallet);
        }
    }
    
    public void decreaseUserBalance(
            Long userId,
            String asset,
            BigDecimal amount
    ) throws RuntimeException{
        CryptoWallet wallet = cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset)
                .orElseThrow(() -> new RuntimeException("The user did not find the required crypto wallet"));
        
        int compareResult = wallet.DecreaseBalance(amount);
        
        if (compareResult < 0) {
            throw new RuntimeException("The sender does not have enough funds " +
                    "to carry out the transfer of cryptocurrency");
        } else {
            if(compareResult == 0){
                cryptoWalletRepository.delete(wallet);
            } else{
                wallet.DecreaseBalance(amount);
                cryptoWalletRepository.save(wallet);
            }
        }
    }
}
