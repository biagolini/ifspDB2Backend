package com.loja.jogos.ecommerce.dto;


import com.loja.jogos.ecommerce.entity.Highlight;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HighlightDto {

    private Long id;
    private Long game;
    private String header;
    private String cover;
    private String description;
    private LocalDate publicationDate;

    public HighlightDto(Highlight highlight) {
        this.id = highlight.getId();
        this.game = highlight.getGame();
        this.header = highlight.getHeader();
        this.cover = highlight.getCover();
        this.description = highlight.getDescription();
        this.publicationDate = highlight.getPublicationDate();
    }

}
