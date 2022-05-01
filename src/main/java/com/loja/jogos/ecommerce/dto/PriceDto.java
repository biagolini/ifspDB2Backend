package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Price;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PriceDto {

    private Long id;
    private Long gamePlatform;
    private LocalDateTime dateTimePublish;
    private double value;

    public PriceDto(Price price) {
        this.id = price.getId();
        this.gamePlatform = price.getGamePlatform();
        this.dateTimePublish = price.getDateTimePublish();
        this.value = price.getValue();
    }

}
