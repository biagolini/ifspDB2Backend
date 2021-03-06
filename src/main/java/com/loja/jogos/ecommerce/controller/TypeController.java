package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.*;
import com.loja.jogos.ecommerce.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("types")
@AllArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping("/getGenre")
    public ResponseEntity<?> getGenre() {
        List<TypeGenreDto> dto = this.typeService.getGenre();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/getPlatform")
    public ResponseEntity<?> getPlatform() {
        List<TypePlatformDto> dto = this.typeService.getPlatform();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/getState")
    public ResponseEntity<?> getState() {
        List<TypeStateDto> dto = this.typeService.getState();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/getStatusOrder")
    public ResponseEntity<?> getStatusOrder() {
        List<TypeStatusOrderDto> dto = this.typeService.getStatusOrder();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/getWarehouseMovement")
    public ResponseEntity<?> getWarehouseMovement() {
        List<TypeWarehouseMovementDto> dto = this.typeService.getWarehouseMovement();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}
