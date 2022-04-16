package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository  extends JpaRepository<Publisher,Long> {
}
