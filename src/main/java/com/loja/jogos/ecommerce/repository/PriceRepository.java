package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository  extends JpaRepository<Price,Long> , JpaSpecificationExecutor<Price> {

    @Query(value = "SELECT   * FROM    tblPrice\n" +
            "WHERE   stActive = 1 \n" +
            "AND idGamePlatform IN (SELECT idGamePlatform FROM tblGamePlatform WHERE  idGame = :idGame AND stActive = 1)" ,  nativeQuery = true)
    List<Price> findByGameID(Long idGame);

    @Query("SELECT a FROM Price a WHERE a.gamePlatform = :gamePlatform  AND a.isActive = true")
    List<Price> findByGamePlatformID(Long gamePlatform);

}
