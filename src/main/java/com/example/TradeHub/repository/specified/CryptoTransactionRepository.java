package com.example.TradeHub.repository.specified;

import com.example.TradeHub.domain.specified.CryptoTransaction;
import org.springframework.data.repository.CrudRepository;

public interface CryptoTransactionRepository extends CrudRepository<CryptoTransaction, Long> {
}
