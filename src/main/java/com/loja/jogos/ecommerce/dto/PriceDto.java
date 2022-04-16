package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {

    private Long id;
    private Long gamePlataform;
    private double value;


    public PriceDto(Price price) {
        this.id = price.getId();
        this.gamePlataform = price.getGamePlataform();
        this.value = price.getValue();
    }
}
