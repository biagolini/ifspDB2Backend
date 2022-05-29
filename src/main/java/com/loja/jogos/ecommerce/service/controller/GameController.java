package com.loja.jogos.ecommerce.service.controller;

import com.loja.jogos.ecommerce.dto.GameDto;
import com.loja.jogos.ecommerce.dto.HighlightDto;
import com.loja.jogos.ecommerce.service.GameService;
import com.loja.jogos.ecommerce.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
@AllArgsConstructor
public class GameController {

    private final ConversionService conversionService;

    private final GameService gameService;

    private final PriceService priceService;

    @GetMapping
    public @ResponseBody
    Page<GameDto> findAllPaginated(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            Pageable pageable // @PageableDefault(sort = "releaseDate", direction = Sort.Direction.DESC, page = 0, size = 10)
    ) {
        Page<GameDto> pageReturnObject;

        if (name == null && genre == null) { // Busca sem nenhum parametro
            pageReturnObject = this.gameService
                                .findAll(pageable)
                                .map(entity -> this.conversionService.convert(entity, GameDto.class));
        } else {
            pageReturnObject = this.gameService // Busca com dados de perfil
                    .findAll(pageable, name, genre)
                    .map(entity -> this.conversionService.convert(entity, GameDto.class));
        }
        pageReturnObject = this.priceService.fillBestPrice(pageReturnObject);
        return pageReturnObject;
    }

    @GetMapping("/platform/{id}")
    public @ResponseBody
    Page<GameDto> findAllPaginated(
            @PathVariable Long id,
            @PageableDefault(sort = "dsReleaseDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        Page<GameDto> pageReturnObject = this.gameService
                .findByidTypePlatform(pageable, id)
                .map(entity -> this.conversionService.convert(entity, GameDto.class));

        pageReturnObject = this.priceService.fillBestPrice(pageReturnObject);
        return pageReturnObject;
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
