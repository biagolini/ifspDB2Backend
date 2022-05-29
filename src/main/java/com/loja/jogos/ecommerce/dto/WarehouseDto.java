package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import com.loja.jogos.ecommerce.entity.WarehouseExit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WarehouseDto {

    private Long id;
    private Long gamePlatform;
    private String gameCover;
    private String gameName;
    private Long idPlatform;
    private Long typeWarehouseMovement;
    private Long quantity;
    private LocalDateTime lastUpdate;

    public WarehouseDto(WarehouseEntrance item) {
        this.id = item.getId();
        this.gamePlatform = item.getGamePlatform().getId();
        this.typeWarehouseMovement = item.getTypeWarehouseMovement();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getEntranceDateTime();
    }

    public WarehouseDto(WarehouseExit item) {
        this.id = item.getId();
        this.gamePlatform = item.getGamePlatform().getId();
        this.typeWarehouseMovement = item.getTypeWarehouseMovement();
        this.quantity = item.getQuantity();
        this.lastUpdate = item.getExitDateTime();
    }

}
