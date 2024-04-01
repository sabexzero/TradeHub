package com.example.TradeHub.service;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.currency.Currency;
import com.example.TradeHub.domain.specified.CryptoTransaction;
import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.specified.CryptoTransactionRepository;
import com.example.TradeHub.repository.user.UserRepository;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final CryptoWalletRepository cryptoWalletRepository;
    private final CryptoTransactionRepository cryptoTransactionRepository;
    
    
    public CryptoWallet buyCryptoCurrency(Long userId, Cryptocurrency cryptocurrencyToBuy, BigDecimal amount, Currency payment, BigDecimal unitPrice){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<CryptoWallet> optionalWallet = cryptoWalletRepository.findByUserAndCryptocurrency(user, cryptocurrencyToBuy);
        
        //Debiting the Currency account in the amount of amount * UnitPrice occurs at the controller level
        
        CryptoWallet resultWallet;
        
        if (optionalWallet.isPresent()) {
            CryptoWallet userWallet = optionalWallet.get();
            userWallet.IncreaseBalance(amount);
            
            resultWallet = cryptoWalletRepository.save(userWallet);
        } else {
            CryptoWallet newUserWallet = CryptoWallet.builder()
                    .balance(amount)
                    .user(user)
                    .cryptocurrency(cryptocurrencyToBuy)
                    .build();
            
            resultWallet = cryptoWalletRepository.save(newUserWallet);
        }
        
        cryptoTransactionRepository.save(
                CryptoTransaction.builder()
                        .baseAsset(payment.baseAsset)
                        .quoteAsset(cryptocurrencyToBuy.baseAsset)
                        .baseAmount(amount.multiply(unitPrice))
                        .quoteAmount(amount)
                        .build()
        );
        
        return resultWallet;
    }
}
