package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.TypeWarehouseMovement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeWarehouseMovementDto {

    private Long id;
    private String descriptionEn;
    private String descriptionPt;

    public TypeWarehouseMovementDto(TypeWarehouseMovement type) {
        this.id = type.getId();
        this.descriptionEn = type.getDescriptionEn();
        this.descriptionPt = type.getDescriptionPt();
    }
}
