package com.example.TradeHub.domain.wallet;

import com.example.TradeHub.domain.currency.Currency;
import com.example.TradeHub.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Schema(description = "The essence of the wallet, each wallet is for a separate currency, " +
        "each user can have many wallets that form his portfolio of currencies")
@Table(value = "wallets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private Long id;
    
    @Column(value = "user_id")
    private Long userId;
    private User user;
    
    private BigDecimal balance;
    
    @Column(value = "currency_id")
    private Long currencyId;
    private Currency currency;
    
    public void IncreaseBalance(BigDecimal value){
        balance = balance.add(value);
    }
    
    public void DecreaseBalance(BigDecimal value){
        balance = balance.subtract(value);
    }
    
}
