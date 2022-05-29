package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import com.loja.jogos.ecommerce.entity.WarehouseExit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WarehouseDto {

    private Long gamePlatform;
    private Long quantity;
    private LocalDateTime lastUpdate;

    public WarehouseDto(WarehouseBalance item) {
        this.gamePlatform = item.getGamePlatform();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getLastUpdate();
    }

    public WarehouseDto(WarehouseEntrance item) {
        this.gamePlatform = item.getGamePlatform();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getEntranceDateTime();
    }

    public WarehouseDto(WarehouseExit item) {
        this.gamePlatform = item.getGamePlatform();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getExitDateTime();
    }

}
