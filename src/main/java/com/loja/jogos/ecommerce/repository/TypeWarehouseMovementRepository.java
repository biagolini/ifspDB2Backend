package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.TypeWarehouseMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeWarehouseMovementRepository  extends JpaRepository<TypeWarehouseMovement,Long>, JpaSpecificationExecutor<TypeWarehouseMovement> {
    @Query(value = "SELECT a FROM TypeWarehouseMovement a WHERE a.isActive = true")
    List<TypeWarehouseMovement> findAll();

}
