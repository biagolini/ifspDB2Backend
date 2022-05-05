package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private Long orderId;
    private Integer quantity;
    private String gameName;
    private Long gameId;
    private Long typePlatformId;
    private String gameCover;
    private Double unityValue;
    private Double subTotal;
    private Long idPrice;


    public ItemDto(Item item) {
        this.id = item.getId();
        this.orderId = item.getOrder().getId();
        this.idPrice = item.getIdPrice();
        this.quantity = item.getQuantity();
    }



}
