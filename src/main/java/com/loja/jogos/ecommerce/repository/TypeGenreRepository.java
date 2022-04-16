package com.loja.jogos.ecommerce.repository;

import com.loja.jogos.ecommerce.entity.TypeGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeGenreRepository  extends JpaRepository<TypeGenre,Long>, JpaSpecificationExecutor<TypeGenre> {

    @Query(value = "SELECT a FROM TypeGenre a WHERE a.isActive = true")
    List<TypeGenre> findAll();
}
