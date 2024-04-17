package com.example.TradeHub.domain.annotations.CryptoTransactionHistory;

import com.example.TradeHub.web.dtos.CryptoUserResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sabextech
 * The methods marked with this annotation will be defined as a cryptocurrency transaction,
 * which must be saved in the transaction history after execution.
 
 * @see CryptoUserResponse
 * To use this annotation, the method must return a CryptoResponse object
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CryptoTransaction {

}
