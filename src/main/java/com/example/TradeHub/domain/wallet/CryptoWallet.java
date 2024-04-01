package com.example.TradeHub.domain.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Schema(description = "The essence of the wallet, each wallet is for a separate currency, " +
        "each user can have many wallets that form his portfolio of cryptocurrencies")
@Table(value = "wallets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWallet {
    @Id
    private Long id;
    
    @Column(value = "user_id")
    private Long userId;
    private User user;
    
    private BigDecimal balance;
    
    @Column(value = "cryptocurrency_id")
    private Long cryptocurrencyId;
    private Cryptocurrency cryptocurrency;
    
    public void IncreaseBalance(BigDecimal value){
        balance = balance.add(value);
    }
    
}
