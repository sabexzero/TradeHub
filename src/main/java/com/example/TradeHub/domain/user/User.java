package com.example.TradeHub.domain.user;

import com.example.TradeHub.domain.wallet.CryptoWallet;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("users")
public class User {
    @Id
    private Long id;
    private String username;
    
    @MappedCollection(idColumn = "user_id")
    private List<CryptoWallet> cryptoWallet;
}
