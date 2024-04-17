package com.example.TradeHub.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

record Asset(
        String asset_id,
        String name,
        boolean type_is_crypto,
        LocalDateTime data_quote_start,
        LocalDateTime data_quote_end,
        LocalDateTime data_orderbook_start,
        LocalDateTime data_orderbook_end,
        LocalDateTime data_trade_start,
        LocalDateTime data_trade_end,
        long data_symbols_count,
        BigDecimal volume_1hrs_usd,
        BigDecimal volume_1day_usd,
        BigDecimal volume_1mth_usd,
        String id_icon,
        String data_start,
        String data_end
) {}

record Exchange(
        LocalDateTime time,
        String asset_id_base,
        String asset_id_quote,
        BigDecimal rate
){}

@Service
public class CoinApiService {
    private final RestTemplate restTemplate;
    
    @Value("${API_GET_EXCHANGE}")
    private String getExchangeUrl;
    
    @Value("${API_GET_ALL_ASSETS}")
    private String getAlLAssetsUrl;
    
    @Value("${COIN_API_KEY}")
    private String apiKey;
    
    private HttpEntity<String> getRequestEntity;
    
    public CoinApiService(){
        restTemplate = new RestTemplate();
        getRequestEntity = null;
    }
    
    @PostConstruct
    private void setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CoinAPI-Key", apiKey);
        getRequestEntity = new HttpEntity<>(headers);
    }
    
    public BigDecimal getCryptocurrencyPrice(
            String baseAsset,
            String quoteAsset
    ) throws RuntimeException {
        BigDecimal result = null;
        
        ResponseEntity<String> response = restTemplate.exchange(
                String.format(getExchangeUrl, baseAsset, quoteAsset),
                HttpMethod.GET,
                getRequestEntity,
                String.class
        );
        
        try {
            ObjectMapper exchangeMapper = new ObjectMapper();
            exchangeMapper.registerModule(new JavaTimeModule());
            Exchange exchange = exchangeMapper.readValue(response.getBody(), Exchange.class);
            
            result = exchange.rate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the price of the cryptocurrency");
        }
        return result;
    }
    
    public List<Pair<String,String>> getAllAssets(){
        List<Pair<String,String>> result = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.exchange(
                getAlLAssetsUrl,
                HttpMethod.GET,
                getRequestEntity,
                String.class
        );
        
        try {
            ObjectMapper assetsMapper = new ObjectMapper();
            assetsMapper.registerModule(new JavaTimeModule());
            List<Asset> assets = assetsMapper.readValue(response.getBody(), new TypeReference<List<Asset>>() {});
            
            for (Asset asset : assets){
                result.add(Pair.of(asset.asset_id(),asset.name()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get assets");
        }
        return result;
    }
}
