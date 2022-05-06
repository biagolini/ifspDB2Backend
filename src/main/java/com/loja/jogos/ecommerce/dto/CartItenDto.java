package com.loja.jogos.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItenDto {

    private String gameCover;
    private Long gameName;
    private Long typePlatformId;
    private Integer quantity;
    private Double unityPrice;
    private Double subTotal;

}
