package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.*;
import com.loja.jogos.ecommerce.entity.Game;
import com.loja.jogos.ecommerce.entity.GamePlatform;
import com.loja.jogos.ecommerce.entity.Media;
import com.loja.jogos.ecommerce.entity.Price;
import com.loja.jogos.ecommerce.repository.GamePlatformRepository;
import com.loja.jogos.ecommerce.repository.GameRepository;
import com.loja.jogos.ecommerce.repository.MediaRepository;
import com.loja.jogos.ecommerce.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

    private final MediaRepository mediaRepository;


    public Page<GameDto> fillBestPrice( Page<GameDto> givenPage) {
        for(GameDto item: givenPage.getContent()){
            item.setBestPrice(this.findBestPrice(item.getId()));
        }
        return givenPage;
    }

    public List<PriceDto> findNewestPrices(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid game id"));
        List<Price> allHistoricPrices = priceRepository.findByGameID(id);
        List<PriceDto> listOfPricesToReturn = new ArrayList<>();

        Boolean testAddNewGamePlatformPrice;
        if(allHistoricPrices.size()>0){
            GamePlatform gamePlatform = gamePlatformRepository.findById(allHistoricPrices.get(0).getGamePlatform()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
            listOfPricesToReturn.add(new PriceDto(allHistoricPrices.get(0),gamePlatform));
            for(int i =1; i<allHistoricPrices.size();i++){ // Para cada um dos pre??os encontrados para o jogo em quest??o
                Long consultPricesGamePlatform = allHistoricPrices.get(i).getGamePlatform(); // Identifica qual ?? a plataforma para o jogo (ex. PlayStation 5 ou XBox)
                LocalDateTime consultPricesDateTime  = allHistoricPrices.get(i).getDateTimePublish(); // Identifica qual ?? a data de publica????o do pre??o
                testAddNewGamePlatformPrice = true;
                gamePlatform = gamePlatformRepository.findById(allHistoricPrices.get(i).getGamePlatform()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gameplatform not found"));
                for(int z = 0; z < listOfPricesToReturn.size();z++) { // Para cada um dos item que j?? est??o na lista que ser?? entregue ao usuario
                    // A Plataforma do item presente  da lista que vai ser entregue ao usuario = a um novo preco da lista de historico de pre??os?
                    // E, a data do pre??o encontrado na pesquisa, foi publicada DEPOIS do pre??o que estava na lista a ser entregue ao cliente?
                    if (consultPricesGamePlatform == listOfPricesToReturn.get(z).getIdGamePlatform()) {
                        // Se em algum momento da pesquisa no historico de pre??o...
                        // foi visto que a plataforma do jogo, j?? est?? com um item presente na lista a ser entregue para o clinete, n??o precisa adiconar na lista
                        testAddNewGamePlatformPrice = false;
                        // Se sim, o item encontrado na pesqquisa foi publicado depois do que est?? dentro da lista a ser entregue, atualize o pre??o a ser entregue
                        if (consultPricesDateTime.isAfter(listOfPricesToReturn.get(z).getDateTimePublish())) {
                            listOfPricesToReturn.set(z, new PriceDto(allHistoricPrices.get(i), gamePlatform));
                        }
                    }
                }
                // Se a plataforma do jogo, n??o estava presente na lista a ser entregue para o clinete, precisa adiconar na lista
                if(testAddNewGamePlatformPrice) {
                    listOfPricesToReturn.add(new PriceDto(allHistoricPrices.get(i),gamePlatform));
                }
            }
        }
        return listOfPricesToReturn;
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

    public GameOfferWrapperDto getOfferProfile(Long id){
        GameOfferWrapperDto dto = new GameOfferWrapperDto();
        Game game = gameRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
        dto.setGame(new GameDto(game));
        dto.getGame().setBestPrice(this.findBestPrice(id));
        List<Media> mediaList = mediaRepository.findByGame(id);
        List<MediaDto> medias= new ArrayList<>();
        for(int i = 0; i<mediaList.size();i++) medias.add(new MediaDto(mediaList.get(i)));
        dto.setMedias(medias);
        List<PriceDto> prices = this.findNewestPrices(id);
        dto.setPrices(prices);
        return dto;
    }

    public void createPrice(PriceForm form) {
        // Desativar pre??os antigos - N??o ?? possivel fazer a desativa????o por um Trigger no SQL, porque n??o ?? possivel atualizar uma tabela quando est?? inserindo dado nela mesma, se n??o voc?? cairia num loop infinito
        List<Price> oldPrices = priceRepository.findByGamePlatformID(form.getIdGamePlatform());
        for(Price item: oldPrices){
            item.setIsActive(false);
            priceRepository.save(item);
        }
        // Criar novo item de pre??o
        Price price = new Price(form);
        priceRepository.save(price);
    }
}
