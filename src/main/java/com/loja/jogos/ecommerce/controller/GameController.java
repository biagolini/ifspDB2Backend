package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.GameDto;
import com.loja.jogos.ecommerce.dto.HighlightDto;
import com.loja.jogos.ecommerce.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game")
@AllArgsConstructor
public class GameController {

    private final ConversionService conversionService;

    private final GameService gameService;

    @GetMapping
    public @ResponseBody
    Page<GameDto> findAllPaginated(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @PageableDefault(sort = "releaseDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        if (name == null && genre == null) { // Busca sem nenhum parametro
            return this.gameService
                    .findAll(pageable)
                    .map(entity -> this.conversionService.convert(entity, GameDto.class));
        }
        return this.gameService // Busca com dados de perfil
                .findAll(pageable, name, genre)
                .map(entity -> this.conversionService.convert(entity, GameDto.class));
    }

    @GetMapping("/platform/{id}")
    public @ResponseBody
    Page<GameDto> findAllPaginated(
            @PathVariable Long id,
            @PageableDefault(sort = "dsReleaseDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return this.gameService
                .findByidTypePlatform(pageable, id)
                .map(entity -> this.conversionService.convert(entity, GameDto.class));
    }



    @GetMapping("/hl")
    public @ResponseBody
    Page<HighlightDto> findAllPaginated(@PageableDefault(sort = "publicationDate", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable customizedPageable)
    {
        return this.gameService
                .findHighlight(customizedPageable)
                .map(entity -> this.conversionService.convert(entity, HighlightDto.class));
    }

}