package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseEntranceRepository  extends JpaRepository<WarehouseEntrance,Long> {
}
