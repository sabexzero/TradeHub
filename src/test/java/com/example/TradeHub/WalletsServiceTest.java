package com.example.TradeHub;

import com.example.TradeHub.domain.user.User;
import com.example.TradeHub.domain.wallet.CryptoWallet;
import com.example.TradeHub.repository.user.UserRepository;
import com.example.TradeHub.repository.wallet.CryptoWalletRepository;
import com.example.TradeHub.service.WalletsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class WalletsServiceTest {
    
    @Mock
    private CryptoWalletRepository cryptoWalletRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private WalletsService walletsService;
    
    
    @Test
    public void testIncreaseUserBalance_NewWallet() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        when(cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset))
                .thenReturn(Optional.empty());
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User()));
        
        // Act
        walletsService.increaseUserBalance(userId, asset, amount);
        
        // Assert
        verify(cryptoWalletRepository).save(any());
    }
    
    @Test
    public void testIncreaseUserBalance_ExistingWallet() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        CryptoWallet existingWallet = CryptoWallet.builder()
                .userId(userId)
                .baseAsset(asset)
                .balance(new BigDecimal("1.0"))
                .build();
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User()));
        
        when(cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset))
                .thenReturn(Optional.of(existingWallet));
        
        // Act
        walletsService.increaseUserBalance(userId, asset, amount);
        
        
        // Assert
        verify(cryptoWalletRepository).save(existingWallet);
    }
    
    @Test
    public void testIncreaseUserBalance_UserNotExist() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            walletsService.increaseUserBalance(userId, asset, amount);
        });
    }
    
    @Test
    public void testDecreaseUserBalance_WalletNotExist() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        when(cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset))
                .thenReturn(Optional.empty());
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User()));
        
        // Act && Assert
        assertThrows(NoSuchElementException.class, () -> {
            walletsService.decreaseUserBalance(userId, asset, amount);
        });
    }
    
    @Test
    public void testDecreaseUserBalance_ExistingWalletAndEnoughFunds() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        CryptoWallet existingWallet = CryptoWallet.builder()
                .userId(userId)
                .baseAsset(asset)
                .balance(new BigDecimal("1.0"))
                .build();
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User()));
        
        when(cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset))
                .thenReturn(Optional.of(existingWallet));
        
        // Act
        walletsService.decreaseUserBalance(userId, asset, amount);
        
        
        // Assert
        verify(cryptoWalletRepository).save(existingWallet);
    }
    
    @Test
    public void testDecreaseUserBalance_ExistingWalletAndNotEnoughFunds() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        
        CryptoWallet existingWallet = CryptoWallet.builder()
                .userId(userId)
                .baseAsset(asset)
                .balance(new BigDecimal("1.0"))
                .build();
        
        BigDecimal amount = existingWallet.getBalance().add(new BigDecimal(1L));
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User()));
        
        when(cryptoWalletRepository.findByUserAndCryptocurrency(userId, asset))
                .thenReturn(Optional.of(existingWallet));
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            walletsService.decreaseUserBalance(userId, asset, amount);
        });
    }
    
    @Test
    public void testDecreaseUserBalance_UserNotExist() {
        // Arrange
        Long userId = 1L;
        String asset = "BTC";
        BigDecimal amount = new BigDecimal("0.1");
        
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            walletsService.decreaseUserBalance(userId, asset, amount);
        });
    }
}