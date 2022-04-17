package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {

    @Query("SELECT a FROM Order a WHERE a.id = :id ")
    Optional<Order> findOrderById(Long id);
}
