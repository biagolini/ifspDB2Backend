package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Publisher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherDto {

    private Long id;
    private String description;
    private String mediaLink;

    public PublisherDto(Publisher publisher) {
        this.id = publisher.getId();
        this.description = publisher.getDescription();
        this.mediaLink = publisher.getMediaLink();
    }
}
