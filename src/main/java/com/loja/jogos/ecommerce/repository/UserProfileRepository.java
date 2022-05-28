package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("SELECT a FROM UserProfile a WHERE a.id = :id")
    Optional<UserProfile> findByEmail(Long id);

}
