package com.example.TradeHub.web;

import com.example.TradeHub.service.CommerceService;
import com.example.TradeHub.service.ConvertCryptoService;
import com.example.TradeHub.web.dtos.CryptoUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class CryptoController {
    private final CommerceService commerceService;
    private final ConvertCryptoService convertCryptoService;
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyCryptocurrency(
            @RequestBody CryptoUserRequest request
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
            @RequestBody CryptoUserRequest request
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
            @RequestBody CryptoUserRequest request
    ){
        try{
            var handledRequest = convertCryptoService.handleConvertRequest(request);
            return new ResponseEntity<>(handledRequest, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
