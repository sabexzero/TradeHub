package com.example.TradeHub.domain.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Schema(description = "The essence of the wallet, each wallet is for a separate currency, " +
        "each user can have many wallets that form his portfolio of cryptocurrencies")
@Table(value = "wallets")
public class CryptoWallet {
    private Long id;
    
    @Column(value = "user_id")
    private Long userId;
    private User user;
    
    private BigDecimal balance;
    
    @Column(value = "cryptocurrency_id")
    private Long cryptocurrencyId;
    private Cryptocurrency cryptocurrency;
    
}
