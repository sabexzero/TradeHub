package com.example.TradeHub.repository.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.wallet.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
}
