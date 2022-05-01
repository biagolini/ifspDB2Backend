package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.GamePlatform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePlatformDto {

    private Long id;
    private Long game;
    private Long platform;
    private String url;

    public GamePlatformDto(GamePlatform gamePlatform) {
        this.id = gamePlatform.getId();
        this.game = gamePlatform.getGame().getId();
        this.platform = gamePlatform.getPlatform().getId();
    }
}
