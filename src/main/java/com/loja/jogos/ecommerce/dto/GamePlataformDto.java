package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.GamePlataform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePlataformDto {

    private Long id;
    private Long game;
    private Long platform;
    private String url;

    public GamePlataformDto(GamePlataform gamePlataform) {
        this.id = gamePlataform.getId();
        this.game = gamePlataform.getGame().getId();
        this.platform = gamePlataform.getPlatform().getId();
    }
}
