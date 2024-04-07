package com.example.TradeHub.web;

import com.example.TradeHub.service.CommerceService;
import com.example.TradeHub.service.TradeCryptoService;
import com.example.TradeHub.web.dtos.buy.CryptoBuyRequest;
import com.example.TradeHub.web.dtos.sell.CryptoSellRequest;
import com.example.TradeHub.web.dtos.trade.CryptoTradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class CryptoController {
    private final CommerceService commerceService;
    private final TradeCryptoService tradeCryptoService;
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyCryptocurrency(
            @RequestBody CryptoBuyRequest request
    ){
        try{
            var handledRequest = commerceService.handleCryptoBuyRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/sell")
    public ResponseEntity<?> sellCryptocurrency(
            @RequestBody CryptoSellRequest request
            ){
        try{
            var handledRequest = commerceService.handleCryptoSellRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/trade")
    public ResponseEntity<?> tradeCryptocurrency(
            @RequestBody CryptoTradeRequest request
    ){
        try{
            var handledRequest = tradeCryptoService.handleTradeRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
