package com.example.TradeHub.repository.cryptocurrency;

import com.example.TradeHub.web.dtos.buy.CompletedCryptoBuyRequest;
import com.example.TradeHub.web.dtos.buy.CryptoBuyRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BuyCryptoRequestRepository extends CrudRepository<CompletedCryptoBuyRequest,Long> {
    /**
     * At this point, we clearly limit the user's ability to buy cryptocurrency:
        until he pays (confirms) the purchase request, he cannot trigger the following
     */
    Optional<CompletedCryptoBuyRequest> findByUserId(Long userId);
}
