package com.example.TradeHub.repository.wallet;

import com.example.TradeHub.domain.currency.Currency;
import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.domain.wallet.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findByUserAndCurrency(User user, Currency currency);
}
