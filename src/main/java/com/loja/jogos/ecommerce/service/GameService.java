package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.GameSummaryDto;
import com.loja.jogos.ecommerce.entity.Game;
import com.loja.jogos.ecommerce.entity.Highlight;
import com.loja.jogos.ecommerce.repository.GameRepository;
import com.loja.jogos.ecommerce.repository.HighlightRepository;
import com.loja.jogos.ecommerce.repository.specifications.GameSpecification;
import com.loja.jogos.ecommerce.repository.specifications.HighlightSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    private final HighlightRepository highlightRepository;

    public Page<Game> findAll(Pageable pageable) {
        return this.gameRepository.findAll(GameSpecification.isActive(), pageable);
    }

    public Page<Game> findAll(Pageable pageable, String name, Long genre) {
        return this.gameRepository.findAll(GameSpecification.likeDescription(name, genre), pageable);
    }

    public Page<Game> findByidTypePlatform(Pageable pageable, Long id) {
        return this.gameRepository.findByidTypePlatform(pageable, id);
    }

    public Page<Highlight> findHighlight(Pageable pageable) {
        return this.highlightRepository.findAll(HighlightSpecification.isActive(), pageable);
    }

    public List<GameSummaryDto> getGamesList() {
        List<Game> allGames = this.gameRepository.findAll();
        List<GameSummaryDto> dto = new ArrayList<>();
        for(Game item: allGames) {
         dto.add(new GameSummaryDto(item));
        }
        return  dto;
    }
}

