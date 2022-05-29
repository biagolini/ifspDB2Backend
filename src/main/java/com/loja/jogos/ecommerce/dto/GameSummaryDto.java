package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSummaryDto {

    private Long id;
    private String name;
    private String cover;

    public GameSummaryDto(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.cover = game.getCover();
    }

}