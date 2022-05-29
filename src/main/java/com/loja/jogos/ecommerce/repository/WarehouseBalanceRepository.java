package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseBalanceRepository  extends JpaRepository<WarehouseBalance,Long>, JpaSpecificationExecutor<WarehouseBalance> {
}
