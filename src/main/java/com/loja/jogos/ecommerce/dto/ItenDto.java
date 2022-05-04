package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Iten;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItenDto {

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


    public ItenDto(Iten iten) {
        this.id = iten.getId();
        this.orderId = iten.getOrder().getId();
        this.idPrice = iten.getIdPrice();
        this.quantity = iten.getQuantity();
    }



}
