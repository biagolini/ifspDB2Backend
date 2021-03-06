package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.*;
import com.loja.jogos.ecommerce.service.CheckJwtInfoService;
import com.loja.jogos.ecommerce.service.GameService;
import com.loja.jogos.ecommerce.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final GameService gameService;

    private final CheckJwtInfoService checkJwtInfoService;

    private final ConversionService conversionService;

    private final WarehouseService warehouseService;


    @GetMapping("/balance")
    public @ResponseBody
    Page<WarehouseBalanceDto> balancePaginated(
            @RequestParam(required = false) String gameName,
            @PageableDefault(sort = "lastUpdate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);

        Page<WarehouseBalanceDto> pageReturnObject;

        if (gameName == null) {
            pageReturnObject =  this.warehouseService.findAllBalance(pageable).map(entity -> this.conversionService.convert(entity, WarehouseBalanceDto.class));
        }else {
            pageReturnObject = this.warehouseService.findAllBalance(pageable, gameName).map(entity -> this.conversionService.convert(entity, WarehouseBalanceDto.class));
        }

        pageReturnObject = this.warehouseService.fillWarehouseBalancePageble(pageReturnObject);

        return pageReturnObject;
    }


    @GetMapping("/entrance")
    public @ResponseBody
    Page<WarehouseDto> entrancePaginated(
            @RequestParam(required = false) String gameName,
            @PageableDefault(sort = "entranceDateTime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);

        Page<WarehouseDto> pageReturnObject;

        if (gameName == null) {
            pageReturnObject =  this.warehouseService.findAllEntrance(pageable).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        } else {
            pageReturnObject =  this.warehouseService.findAllEntrance(pageable, gameName).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        }

        pageReturnObject = this.warehouseService.fillWarehousePageble(pageReturnObject);

        return pageReturnObject;
    }



    @GetMapping("/exit")
    public @ResponseBody
    Page<WarehouseDto> exitPaginated(
            @RequestParam(required = false) String gameName,
            @PageableDefault(sort = "exitDateTime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);
        Page<WarehouseDto> pageReturnObject;

        if (gameName == null) {
            pageReturnObject =  this.warehouseService.findAllExit(pageable).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        } else {
            pageReturnObject =  this.warehouseService.findAllExit(pageable, gameName).map(entity -> this.conversionService.convert(entity, WarehouseDto.class));
        }

        pageReturnObject = this.warehouseService.fillWarehousePageble(pageReturnObject);

        return pageReturnObject;
    }

    @GetMapping("/gameList")
    public ResponseEntity<?> getGameList() {
        List<GameSummaryDto> dto = this.gameService.getGamesList();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



    @PostMapping("/entrance")
    public ResponseEntity<?> postEntrance(@RequestBody WarehouseMovementForm form,
                                         @RequestHeader (name="Authorization") String token) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);
        this.warehouseService.postEntrance(form,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/exit")
    public ResponseEntity<?> postExit(@RequestBody WarehouseMovementForm form,
                                          @RequestHeader (name="Authorization") String token) {
        this.checkJwtInfoService.blockNotAdminEstoque(token);
        this.warehouseService.postExit(form,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
