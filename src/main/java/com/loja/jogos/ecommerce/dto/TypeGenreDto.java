package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.TypeGenre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeGenreDto {

    private Long id;
    private String descriptionEn;
    private String descriptionPt;

    public TypeGenreDto(TypeGenre typeGenre) {
        this.id = typeGenre.getId();
        this.descriptionEn = typeGenre.getDescriptionEn();
        this.descriptionPt = typeGenre.getDescriptionPt();
    }
}
