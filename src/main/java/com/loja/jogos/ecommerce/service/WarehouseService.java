package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import com.loja.jogos.ecommerce.entity.WarehouseExit;
import com.loja.jogos.ecommerce.repository.WarehouseBalanceRepository;
import com.loja.jogos.ecommerce.repository.WarehouseEntranceRepository;
import com.loja.jogos.ecommerce.repository.WarehouseExitRepository;
import com.loja.jogos.ecommerce.repository.specifications.WarehouseSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WarehouseService {

    private final WarehouseBalanceRepository warehouseBalanceRepository;

    private final WarehouseEntranceRepository warehouseEntranceRepository;

    private final WarehouseExitRepository warehouseExitRepository;

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


}
