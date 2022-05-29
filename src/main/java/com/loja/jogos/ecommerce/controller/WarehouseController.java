package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.CustomerDto;
import com.loja.jogos.ecommerce.dto.WarehouseDto;
import com.loja.jogos.ecommerce.service.CheckJwtInfoService;
import com.loja.jogos.ecommerce.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final CheckJwtInfoService checkJwtInfoService;

    private final ConversionService conversionService;

    private final WarehouseService warehouseService;


    @GetMapping("/balance")
    public @ResponseBody
    Page<WarehouseDto> balancePaginated(
            @RequestParam(required = false) Long gamePlatform,
            Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);

        if (gamePlatform == null) {
            return this.warehouseService.findAllBalance(pageable).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        }
        return this.warehouseService.findAllBalance(pageable, gamePlatform).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
    }


    @GetMapping("/entrance")
    public @ResponseBody
    Page<WarehouseDto> entrancePaginated(
            @RequestParam(required = false) Long gamePlatform,
            Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);

        if (gamePlatform == null) {
            return this.warehouseService.findAllEntrance(pageable).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        }
        return this.warehouseService.findAllEntrance(pageable, gamePlatform).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
    }



    @GetMapping("/exit")
    public @ResponseBody
    Page<WarehouseDto> exitPaginated(
            @RequestParam(required = false) Long gamePlatform,
            Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);

        if (gamePlatform == null) {
            return this.warehouseService.findAllExit(pageable).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        }
        return this.warehouseService.findAllExit(pageable, gamePlatform).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
    }


}
