package com.example.TradeHub.domain.annotations.CryptoTransactionHistory;

import com.example.TradeHub.domain.specified.CryptoTransactionHistoryElement;
import com.example.TradeHub.domain.specified.TransactionType;
import com.example.TradeHub.repository.specified.CryptoTransactionRepository;
import com.example.TradeHub.web.dtos.CryptoResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



@Aspect
@Component
@RequiredArgsConstructor
public class CryptoTransactionAspect {
    private final CryptoTransactionRepository cryptoTransactionRepository;
    
    @AfterReturning(value = "@annotation(CryptoTransaction)", returning = "result")
    public void afterCryptoTransaction(
            JoinPoint joinPoint,
            Object result
    ) {
        
        String methodName = joinPoint.getSignature().getName();
        
        //Check return type
        if (!(result instanceof CryptoResponse cryptoResponse)) {
            throw new RuntimeException("The result of the method marked as CryptoTransaction is not a CryptoResponse object");
        }
        
        if (!cryptoResponse.isSuccessful()){
            //Log about unsuccessful payment operation
            return;
        }
        
        //Determine transaction type
        for(TransactionType transactionType : TransactionType.values()){
            if(methodName.toUpperCase().contains(transactionType.name())){
                cryptoTransactionRepository.save(
                        CryptoTransactionHistoryElement.builder()
                                .userId(cryptoResponse.userId())
                                .transactionType(transactionType.name())
                                .baseAsset(cryptoResponse.baseAsset())
                                .quoteAsset(cryptoResponse.quoteAsset())
                                .baseAmount(cryptoResponse.baseAmount())
                                .quoteAmount(cryptoResponse.quoteAmount())
                                .build()
                );
            }
        }
        
    }
}
