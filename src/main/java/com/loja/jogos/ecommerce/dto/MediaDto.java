package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Media;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaDto {

    private Long id;
    private Long game;
    private Boolean isVideo;
    private String url;

    public MediaDto(Media media) {
        this.id = media.getId();
        this.game = media.getGame();
        this.isVideo = media.getIsVideo();
        this.url = media.getUrl();
    }
}
