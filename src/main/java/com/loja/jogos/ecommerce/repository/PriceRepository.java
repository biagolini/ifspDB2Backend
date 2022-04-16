package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository  extends JpaRepository<Price,Long> {
}
