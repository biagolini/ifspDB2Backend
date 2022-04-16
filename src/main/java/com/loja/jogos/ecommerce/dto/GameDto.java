package com.loja.jogos.ecommerce.dto;


import com.loja.jogos.ecommerce.entity.Game;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GameDto {

    private Long id;
    private String name;
    private LocalDate releaseDate;
    private Long genre;
    private Long publisher;
    private String cover;

    public GameDto(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.releaseDate = game.getReleaseDate();
        this.genre = game.getGenre();
        this.publisher = game.getPublisher();
        this.cover = game.getCover();
    }
}
