package org.example.backendshop.repositores;

import org.example.backendshop.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select us FROM User us where us.account = :loginCheck or us.numberPhone = :loginCheck or us.email = :loginCheck and us.status = 1")
    Optional<User> findByLogin(@Param("loginCheck") String login);
}
