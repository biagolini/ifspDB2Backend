package com.loja.jogos.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameOfferWrapperDto {
    private GameDto game;
    private List<MediaDto> medias;
    private List<PriceDto> prices;

}
