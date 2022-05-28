package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT a FROM User a WHERE a.email = :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT a FROM User a WHERE a.id = :id AND a.isActive = true")
    Optional<User> findById(Long id);

}
