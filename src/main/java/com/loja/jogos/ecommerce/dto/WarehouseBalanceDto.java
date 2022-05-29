package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WarehouseBalanceDto {

    private Long gamePlatform;
    private String gameCover;
    private String gameName;
    private Long idPlatform;
    private Long quantity;
    private LocalDateTime lastUpdate;

    public WarehouseBalanceDto(WarehouseBalance item) {
        this.gamePlatform = item.getGamePlatform().getId();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getLastUpdate();
    }


}
