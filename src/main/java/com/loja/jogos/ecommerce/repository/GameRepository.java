package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> , JpaSpecificationExecutor<Game> {

    @Query(value = "SELECT   * FROM    tblGame\n" +
            "WHERE   stActive = 1 \n" +
            "AND idGame IN (SELECT idGame FROM tblGamePlatform WHERE  idTypePlatform = :id AND stActive = 1)" ,  nativeQuery = true)
    Page<Game> findByidTypePlatform(Pageable pageable, Long id);

    @Query("SELECT a FROM Game a WHERE a.id = :id AND a.isActive = true")
    Optional<Game> findById(Long id);


}
