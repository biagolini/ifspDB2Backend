package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {

    @Query("SELECT a FROM Media a WHERE a.game = :game  AND a.isActive = true")
    List<Media> findByGame(Long game);

}
