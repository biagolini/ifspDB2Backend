package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.CustomerForm;
import com.loja.jogos.ecommerce.dto.GameOfferWrapperDto;
import com.loja.jogos.ecommerce.dto.PriceDto;
import com.loja.jogos.ecommerce.dto.PriceForm;
import com.loja.jogos.ecommerce.service.CheckJwtInfoService;
import com.loja.jogos.ecommerce.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("price")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    private final CheckJwtInfoService checkJwtInfoService;


    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody PriceForm form, @RequestHeader (name="Authorization") String token) {
        this.checkJwtInfoService.blockNotAdmin(token);
        this.priceService.createPrice(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/bpi/{id}")
    public @ResponseBody
    ResponseEntity<Double> findBestPrice(@PathVariable Long id){
        Double response = this.priceService.findBestPrice(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/pricesOfGame/{id}")
    public @ResponseBody
    ResponseEntity<List<PriceDto>> findNewestPrices(@PathVariable Long id){
        List<PriceDto> response = this.priceService.findNewestPrices(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/profile/{id}")
    public @ResponseBody
    ResponseEntity<GameOfferWrapperDto> getOfferProfile(@PathVariable Long id) {
        GameOfferWrapperDto response = this.priceService.getOfferProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
