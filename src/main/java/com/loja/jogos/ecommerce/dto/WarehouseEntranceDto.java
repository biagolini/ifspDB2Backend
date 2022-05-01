package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseEntranceDto {

    private Long id;
    private Long gamePlatform;
    private Long quantity;

    public WarehouseEntranceDto(WarehouseEntrance warehouseEntrancey) {
        this.id = warehouseEntrancey.getId();
        this.gamePlatform = warehouseEntrancey.getGamePlatform();
        this.quantity = warehouseEntrancey.getQuantity();
    }
}
