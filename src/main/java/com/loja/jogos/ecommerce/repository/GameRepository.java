package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> , JpaSpecificationExecutor<Game> {
}
