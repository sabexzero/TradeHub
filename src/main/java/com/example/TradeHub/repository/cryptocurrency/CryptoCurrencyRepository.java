package com.example.TradeHub.repository.cryptocurrency;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CryptoCurrencyRepository extends CrudRepository<Cryptocurrency,Long> {
}
