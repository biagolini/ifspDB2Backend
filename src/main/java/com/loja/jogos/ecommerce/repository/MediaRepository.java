package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {

}
