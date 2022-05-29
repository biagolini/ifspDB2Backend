package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.PriceDto;
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

import java.util.List;

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

    public Page<WarehouseBalance> findAllBalance(Pageable pageable, Long gamePlatform) {
        return this.warehouseBalanceRepository.findAll(WarehouseSpecification.balanceGamePlatformEquals(gamePlatform), pageable);
    }

    public Page<WarehouseEntrance> findAllEntrance(Pageable pageable) {
        return this.warehouseEntranceRepository.findAll(pageable);
    }

    public Page<WarehouseEntrance> findAllEntrance(Pageable pageable, Long gamePlatform) {
        return this.warehouseEntranceRepository.findAll(WarehouseSpecification.entranceGamePlatformEquals(gamePlatform), pageable);
    }

    public Page<WarehouseExit> findAllExit(Pageable pageable) {
        return this.warehouseExitRepository.findAll(pageable);
    }

    public Page<WarehouseExit> findAllExit(Pageable pageable, Long gamePlatform) {
        return this.warehouseExitRepository.findAll(WarehouseSpecification.exitGamePlatformEquals(gamePlatform), pageable);
    }

    public Page<WarehouseBalanceDto> fillWarehouseBalancePageble(Page<WarehouseBalanceDto> givenPage) {
        for(WarehouseBalanceDto item: givenPage.getContent()){
            GamePlatform gamePlatform = gamePlatformRepository.findById(item.getGamePlatform() ).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
            Game game = gameRepository.findById(gamePlatform.getGame().getId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
            item.setTypePlatformId(gamePlatform.getPlatform().getId());
            item.setGameName(game.getName());
            item.setGameCover(game.getCover());
        }
        return givenPage;
    }



    public Page<WarehouseDto> fillWarehousePageble(Page<WarehouseDto> givenPage) {
        for(WarehouseDto item: givenPage.getContent()){
            GamePlatform gamePlatform = gamePlatformRepository.findById(item.getGamePlatform() ).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
            Game game = gameRepository.findById(gamePlatform.getGame().getId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
            item.setTypePlatformId(gamePlatform.getPlatform().getId());
            item.setGameName(game.getName());
            item.setGameCover(game.getCover());
        }
        return givenPage;
    }
}
