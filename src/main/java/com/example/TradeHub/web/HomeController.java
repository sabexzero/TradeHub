package com.example.TradeHub.web;

import com.example.TradeHub.repository.cryptocurrency.CryptoCurrencyRepository;
import com.example.TradeHub.repository.currency.CurrencyRepository;
import com.example.TradeHub.repository.wallet.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
@Controller
public class HomeController {
    
}
