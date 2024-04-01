package com.example.TradeHub.repository.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CryptoWalletRepository extends CrudRepository<CryptoWallet,Long> {
    Optional<CryptoWallet> findByUserAndCryptocurrency(User user, Cryptocurrency cryptocurrency);
}
