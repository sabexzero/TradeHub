package com.example.TradeHub.repository.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CryptoWalletRepository extends CrudRepository<CryptoWallet,Long> {
    @Query("SELECT * FROM wallets WHERE user_id = :userId AND base_asset = :baseAsset")
    Optional<CryptoWallet> findByUserAndCryptocurrency(
            @Param("userId") Long userId,
            @Param("baseAsset") String baseAsset
    );
    
    @Query("SELECT * FROM wallets WHERE cryptocurrency_id = :cryptocurrencyId")
    Optional<CryptoWallet> findByCryptocurrency(
            @Param("cryptocurrencyId") Long cryptocurrencyId
    );
}
