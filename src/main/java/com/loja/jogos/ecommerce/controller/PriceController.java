package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.PriceDto;
import com.loja.jogos.ecommerce.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/price")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/bpi/{id}")
    public @ResponseBody
    ResponseEntity<Double> findBestPrice(@PathVariable Long id){
        Double response = this.priceService.findBestPrice(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/bpl/{id}")
    public @ResponseBody
    ResponseEntity<List<PriceDto>> findNewestPrices(@PathVariable Long id){
        List<PriceDto> response = this.priceService.findNewestPrices(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
