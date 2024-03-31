package com.example.TradeHub.repository.currency;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.currency.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CurrencyRepository extends CrudRepository<Currency,Long> {
}
