package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Iten;
import com.loja.jogos.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItenRepository extends JpaRepository<Iten, Long>  {

    @Query("SELECT a FROM Iten a WHERE a.order = :order")
    List<Iten> findByOrder(Order order);

}
