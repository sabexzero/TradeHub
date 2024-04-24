package com.example.TradeHub.service;

import com.example.TradeHub.domain.annotations.CryptoTransactionHistory.CryptoTransaction;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.web.dtos.CryptoUserRequest;
import com.example.TradeHub.web.dtos.CryptoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConvertCryptoService {
    private final CryptoWalletRepository cryptoWalletRepository;
    private final CoinApiService coinApiService;
    
    //TODO: Simplify or decompose the method
    // labels: refactoring
    @Transactional
    @CryptoTransaction
    public CryptoUserResponse handleConvertRequest(
            CryptoUserRequest request
    ) throws IOException {
        //We receive a wallet from which the cryptocurrency will be debited
        CryptoWallet walletToGive = cryptoWalletRepository.findByUserAndCryptocurrency(
                request.userId(),
                request.baseAsset()
        ).orElseThrow(() -> new RuntimeException("The user does not have the necessary wallet"));
        
        //We receive a wallet to which the cryptocurrency will be credited
        Optional<CryptoWallet> walletToGet = cryptoWalletRepository.findByUserAndCryptocurrency(
                request.userId(),
                request.quoteAsset()
        );
        
        BigDecimal receivedCryptoPrice = coinApiService.getCryptocurrencyPrice(request.baseAsset(), request.quoteAsset());
        
        //We are trying to write off the cryptocurrency
        BigDecimal withdrawn = request.amount().multiply(receivedCryptoPrice);
        
        int changeBalanceResult = walletToGive.DecreaseBalance(request.amount());
        
        //We react to the result of the operation
        if(changeBalanceResult >= 0){
            if(changeBalanceResult == 1){
                cryptoWalletRepository.save(walletToGive);
            }
            else{
                cryptoWalletRepository.delete(walletToGive);
            }
        }
        else{
            return new CryptoUserResponse(request, receivedCryptoPrice, false);
        }
        
        //Adding cryptocurrencies to the target wallet
        if(walletToGet.isPresent()){
            CryptoWallet checkedWallet = walletToGet.get();
            checkedWallet.IncreaseBalance(withdrawn);
            
            cryptoWalletRepository.save(checkedWallet);
        } else {
            CryptoWallet cryptoWallet = CryptoWallet.builder()
                    .balance(withdrawn)
                    .baseAsset(request.quoteAsset())
                    .userId(request.userId())
                    .build();
            cryptoWalletRepository.save(cryptoWallet);
        }
        
        return new CryptoUserResponse(request, withdrawn, true);
    }
}
