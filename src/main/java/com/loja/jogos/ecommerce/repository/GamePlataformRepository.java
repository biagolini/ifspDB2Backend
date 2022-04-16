package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.GamePlataform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlataformRepository extends JpaRepository<GamePlataform,Long> {
}
