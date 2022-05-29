package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.WarehouseBalanceDto;
import com.loja.jogos.ecommerce.dto.WarehouseDto;
import com.loja.jogos.ecommerce.entity.*;
import com.loja.jogos.ecommerce.repository.*;
import com.loja.jogos.ecommerce.repository.specifications.WarehouseSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class WarehouseService {

    private final WarehouseBalanceRepository warehouseBalanceRepository;

    private final WarehouseEntranceRepository warehouseEntranceRepository;

    private final WarehouseExitRepository warehouseExitRepository;

    private final GameRepository gameRepository;

    private final GamePlatformRepository gamePlatformRepository;

    public Page<WarehouseBalance> findAllBalance(Pageable pageable) {
        return this.warehouseBalanceRepository.findAll(pageable);
    }

    public Page<WarehouseBalance> findAllBalance(Pageable pageable, String gameName) {
        return this.warehouseBalanceRepository.findAll(WarehouseSpecification.balanceGamePlatformLike(gameName), pageable);
    }

    public Page<WarehouseEntrance> findAllEntrance(Pageable pageable) {
        return this.warehouseEntranceRepository.findAll(pageable);
    }

    public Page<WarehouseEntrance> findAllEntrance(Pageable pageable, String gameName) {
        return this.warehouseEntranceRepository.findAll(WarehouseSpecification.entranceGamePlatformLike(gameName), pageable);
    }

    public Page<WarehouseExit> findAllExit(Pageable pageable) {
        return this.warehouseExitRepository.findAll(pageable);
    }

    public Page<WarehouseExit> findAllExit(Pageable pageable, String gameName) {
        return this.warehouseExitRepository.findAll(WarehouseSpecification.exitGamePlatformLike(gameName), pageable);
    }

    public Page<WarehouseBalanceDto> fillWarehouseBalancePageble(Page<WarehouseBalanceDto> givenPage) {
        for(WarehouseBalanceDto item: givenPage.getContent()){
            GamePlatform gamePlatform = gamePlatformRepository.findById(item.getGamePlatform() ).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
            Game game = gameRepository.findById(gamePlatform.getGame().getId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
            item.setIdPlatform(gamePlatform.getPlatform().getId());
            item.setGameName(game.getName());
            item.setGameCover(game.getCover());
        }
        return givenPage;
    }



    public Page<WarehouseDto> fillWarehousePageble(Page<WarehouseDto> givenPage) {
        for(WarehouseDto item: givenPage.getContent()){
            GamePlatform gamePlatform = gamePlatformRepository.findById(item.getGamePlatform() ).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
            Game game = gameRepository.findById(gamePlatform.getGame().getId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
            item.setIdPlatform(gamePlatform.getPlatform().getId());
            item.setGameName(game.getName());
            item.setGameCover(game.getCover());
        }
        return givenPage;
    }
}
