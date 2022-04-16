package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.TypePlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypePlatformRepository  extends JpaRepository<TypePlatform,Long> {

    @Query(value = "SELECT a FROM TypePlatform a WHERE a.isActive = true")
    List<TypePlatform> findAll();

}
