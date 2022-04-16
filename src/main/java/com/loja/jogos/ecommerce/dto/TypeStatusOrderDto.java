package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.TypeStatusOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeStatusOrderDto {

    private Long id;
    private String descriptionEn;
    private String descriptionPt;

    public TypeStatusOrderDto(TypeStatusOrder typeStatusOrder) {
        this.id = typeStatusOrder.getId();
        this.descriptionEn = typeStatusOrder.getDescriptionEn();
        this.descriptionPt = typeStatusOrder.getDescriptionPt();
    }
}
