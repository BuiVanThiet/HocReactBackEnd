package org.example.backendshop.repositores;


import org.example.backendshop.entites.BlacklistToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, Integer> {
    Optional<BlacklistToken> findByToken(String token);
}
