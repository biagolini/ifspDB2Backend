package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.TypeState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TypeStateRepository extends JpaRepository<TypeState,Long> {

    @Query(value = "SELECT a FROM TypeState a WHERE a.isActive = true")
    List<TypeState> findAll();

    @Query("SELECT a FROM TypeState a WHERE a.id = :id AND a.isActive = true")
    Optional<TypeState> findById(Long id);


}
