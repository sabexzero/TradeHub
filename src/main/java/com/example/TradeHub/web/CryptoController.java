package com.example.TradeHub.web;

import com.example.TradeHub.service.CommerceService;
import com.example.TradeHub.service.TradeCryptoService;
import com.example.TradeHub.web.dtos.CryptoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class CryptoController {
    private final CommerceService commerceService;
    private final TradeCryptoService tradeCryptoService;
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyCryptocurrency(
            @RequestBody CryptoRequest request
    ){
        try{
            var handledRequest = commerceService.handleCryptoBuyRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/confirm/{requestId}")
    public ResponseEntity<?> confirmBuyRequest(
            @PathVariable Long requestId
    ){
        try{
            var handledRequest = commerceService.confirmCryptoBuyRequest(requestId);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/sell")
    public ResponseEntity<?> sellCryptocurrency(
            @RequestBody CryptoRequest request
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
            @RequestBody CryptoRequest request
    ){
        try{
            var handledRequest = tradeCryptoService.handleTradeRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
