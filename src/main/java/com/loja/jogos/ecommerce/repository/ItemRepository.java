package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Item;
import com.loja.jogos.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>  {

    @Query("SELECT a FROM Item a WHERE a.order = :order")
    List<Item> findByOrder(Order order);

}
