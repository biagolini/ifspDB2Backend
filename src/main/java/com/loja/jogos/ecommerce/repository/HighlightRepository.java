package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightRepository extends JpaRepository<Highlight,Long> , JpaSpecificationExecutor<Highlight> {

}
