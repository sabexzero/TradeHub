package com.example.TradeHub.domain.cryptocurrency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Schema(description = "The essence of the cryptocurrency, " +
                        "domain-specific fields were described separately")
@Table("cryptocurrencies")
public class Cryptocurrency {
    @Id
    private Long id;
    
    @Schema(description = "Contains the code that will be needed to get information using NinjasApi")
    @Column(value = "base_asset")
    private String baseAsset;
}
