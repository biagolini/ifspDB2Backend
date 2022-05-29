package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.GamePlatform;
import com.loja.jogos.ecommerce.entity.Price;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PriceDto {

    private Long idPrice;
    private Long idGamePlatform;
    private Long idPlatform;
    private LocalDateTime dateTimePublish;
    private double value;

    public PriceDto(Price price, GamePlatform gamePlatform) {
        this.idPrice = price.getId();
        this.idGamePlatform = price.getGamePlatform();
        this.idPlatform = gamePlatform.getPlatform().getId();
        this.dateTimePublish = price.getDateTimePublish();
        this.value = price.getValue();
    }

}
