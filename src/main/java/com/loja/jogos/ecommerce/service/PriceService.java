package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.PriceDto;
import com.loja.jogos.ecommerce.entity.Game;
import com.loja.jogos.ecommerce.entity.Price;
import com.loja.jogos.ecommerce.repository.GamePlatformRepository;
import com.loja.jogos.ecommerce.repository.GameRepository;
import com.loja.jogos.ecommerce.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PriceService {

    private final GameRepository gameRepository;

    private final GamePlatformRepository gamePlatformRepository;

    private final PriceRepository priceRepository;


    public List<PriceDto> findNewestPrices(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid game id"));
        List<Price> prices = priceRepository.findByGameID(id);
        List<PriceDto> newestPrices = new ArrayList<>();

        Boolean testAddNewGamePlatformPrice;
        if(prices.size()>0){
            newestPrices.add(new PriceDto(prices.get(0)));
            for(int i =1; i<prices.size();i++){
                Long currentGamePlatform = prices.get(i).getGamePlatform();
                LocalDateTime currentDateTime  = prices.get(i).getDateTimePublish();
                testAddNewGamePlatformPrice = true;
                for(int z = 0; z < newestPrices.size();z++){
                    if(currentGamePlatform==newestPrices.get(z).getGamePlatform()&&currentDateTime.isAfter(newestPrices.get(z).getDateTimePublish())){
                        newestPrices.set(z, new PriceDto(prices.get(i)));
                    }
                    if(currentGamePlatform==newestPrices.get(z).getGamePlatform()) testAddNewGamePlatformPrice = false;
                }
                if(testAddNewGamePlatformPrice)  newestPrices.add(new PriceDto(prices.get(i)));
            }
        }
        return newestPrices;
    }

    public Double findBestPrice(Long id) {
        Double bestprice=null;
        List<PriceDto> prices = this.findNewestPrices(id);
        if(prices.size()>0)  bestprice = prices.get(0).getValue();
        if(prices.size()>1) {
            for(int i = 1; i<prices.size();i++){
                if(prices.get(i).getValue()<bestprice) bestprice = prices.get(i).getValue();
            }
        }

        return bestprice;
    }



}