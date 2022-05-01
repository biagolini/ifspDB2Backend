package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Game;
import com.loja.jogos.ecommerce.entity.GamePlatform;
import com.loja.jogos.ecommerce.entity.Price;
import com.loja.jogos.ecommerce.entity.TypePlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamePlatformRepository extends JpaRepository<GamePlatform,Long> {


    @Query("SELECT a FROM GamePlatform a WHERE a.game = :game AND a.platform = :platform AND a.isActive = true")
    Optional<GamePlatform> findGamePlatform(Game game, TypePlatform platform );

}
