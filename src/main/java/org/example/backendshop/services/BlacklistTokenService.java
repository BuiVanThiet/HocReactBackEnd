package org.example.backendshop.services;

import lombok.RequiredArgsConstructor;
import org.example.backendshop.entites.BlacklistToken;
import org.example.backendshop.repositores.BlacklistTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BlacklistTokenService {
    private final BlacklistTokenRepository blacklistTokenRepository;

    public void addToBlacklist(String token, LocalDateTime expiredAt) {
        BlacklistToken blacklistToken = new BlacklistToken();
        blacklistToken.setToken(token);
        blacklistToken.setExpiredAt(expiredAt);
        blacklistTokenRepository.save(blacklistToken);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistTokenRepository.findByToken(token).isPresent();
    }
}
