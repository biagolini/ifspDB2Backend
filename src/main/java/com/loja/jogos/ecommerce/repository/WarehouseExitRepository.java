package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.WarehouseExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseExitRepository  extends JpaRepository<WarehouseExit,Long> , JpaSpecificationExecutor<WarehouseExit> {

    @Query(value = "SELECT a FROM WarehouseExit a")
    List<WarehouseExit> findAll();


}
