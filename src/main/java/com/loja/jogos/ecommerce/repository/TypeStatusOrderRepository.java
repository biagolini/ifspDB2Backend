package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.TypeStatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeStatusOrderRepository  extends JpaRepository<TypeStatusOrder,Long> {

    @Query(value = "SELECT a FROM TypeStatusOrder a WHERE a.isActive = true")
    List<TypeStatusOrder> findAll();
}
