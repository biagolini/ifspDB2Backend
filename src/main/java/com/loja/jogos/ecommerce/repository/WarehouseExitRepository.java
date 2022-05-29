package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.WarehouseExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseExitRepository  extends JpaRepository<WarehouseExit,Long> , JpaSpecificationExecutor<WarehouseExit> {
}
