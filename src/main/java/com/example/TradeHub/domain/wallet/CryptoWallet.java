package com.example.TradeHub.domain.wallet;

import com.example.TradeHub.domain.cryptocurrency.Cryptocurrency;
import com.example.TradeHub.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Schema(description = "The essence of the wallet, each wallet is for a separate currency, " +
        "each user can have many wallets that form his portfolio of cryptocurrencies")
@Table(value = "wallets")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWallet {
    @Id
    private Long id;
    
    @Column(value = "user_id")
    private Long userId;
    
    private BigDecimal balance;
    
    private String base_asset;
    
    public void IncreaseBalance(BigDecimal value){
        balance = balance.add(value);
    }
    
    /**
     *
     * @param value - The value that is going to be taken away from the balance
     * @return
       -1, then the amount could not be deducted, the balance has not changed
       0, the operation is successful, but the balance has dropped to zero, there is no need to store the wallet in the database
       1, the operation is successful, the balance is positive
     */
    public int DecreaseBalance(BigDecimal value){
        int result = balance.compareTo(value);
        
        //It means that the value that is going to be taken
        // away is no more than the balance of the user's wallet
        if(balance.compareTo(value) > 0)
            balance = balance.subtract(value);
        
        return result;
    }
    
}
