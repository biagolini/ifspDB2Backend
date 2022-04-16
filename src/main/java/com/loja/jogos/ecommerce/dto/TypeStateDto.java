package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.TypeState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeStateDto {

    private Long id;
    private String description;
    private String name;

    public TypeStateDto(TypeState typeState) {
        this.id = typeState.getId();
        this.description = typeState.getDescription();
        this.name = typeState.getName();
    }
}
