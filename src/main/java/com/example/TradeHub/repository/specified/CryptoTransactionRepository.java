package com.example.TradeHub.repository.specified;

import com.example.TradeHub.domain.specified.CryptoTransactionHistoryElement;
import org.springframework.data.repository.CrudRepository;

public interface CryptoTransactionRepository extends CrudRepository<CryptoTransactionHistoryElement, Long> {
}
