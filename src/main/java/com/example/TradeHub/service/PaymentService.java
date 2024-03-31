package com.example.TradeHub.service;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.currency.Currency;
import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.Wallet;
import com.example.TradeHub.repository.user.UserRepository;
import com.example.TradeHub.repository.wallet.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public final UserRepository userRepository;
    public final WalletRepository walletRepository;
    
    public Wallet depositBalance(Long userId, Currency currency, BigDecimal amount){
        User userToDeposit = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Wallet> optionalWallet = walletRepository.findByUserAndCurrency(userToDeposit, currency);
        
        if (optionalWallet.isPresent()) {
            Wallet userWallet = optionalWallet.get();
            userWallet.IncreaseBalance(amount);
            
            return walletRepository.save(userWallet);
        } else {
            Wallet newUserWallet = Wallet.builder()
                    .balance(amount)
                    .user(userToDeposit)
                    .currency(currency)
                    .build();
            
            return walletRepository.save(newUserWallet);
        }
    }
    public User buyCryptoCurrency(Long userId, Cryptocurrency cryptocurrency, BigDecimal amount){
        //TODO: Implemented method to buy cryptocurrency
        return new User();
    }
}
