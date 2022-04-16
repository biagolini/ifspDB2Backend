package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.TypePlatform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypePlatformDto {

    private Long id;
    private String description;

    public TypePlatformDto(TypePlatform typePlatform) {
        this.id = typePlatform.getId();
        this.description = typePlatform.getDescription();
    }
}
