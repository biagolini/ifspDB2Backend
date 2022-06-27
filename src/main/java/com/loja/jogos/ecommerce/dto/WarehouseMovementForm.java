package com.loja.jogos.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseMovementForm {

    private Long idGamePlatform;
    private Long idTypeWarehouseMovement;
    private Long quantity;

}
