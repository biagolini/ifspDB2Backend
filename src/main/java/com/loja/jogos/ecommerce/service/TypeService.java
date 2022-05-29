package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.*;
import com.loja.jogos.ecommerce.entity.*;
import com.loja.jogos.ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TypeService {

    private final TypeGenreRepository typeGenreRepository;
    private final TypePlatformRepository typePlatformRepository;
    private final TypeStateRepository typeStateRepository;
    private final TypeStatusOrderRepository typeStatusOrderRepository;
    private final TypeWarehouseMovementRepository typeWarehouseMovementRepository;

    public List<TypeGenreDto> getGenre(){
        List<TypeGenre> listTypes = typeGenreRepository.findAll();

        if(listTypes.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TypeGenreDto> listReturn = new ArrayList<>();
        for (int i = 0; i < listTypes.size(); i++) {
            listReturn.add(new TypeGenreDto(listTypes.get(i) ));
        }
        return listReturn;
    }

    public List<TypePlatformDto> getPlatform(){
        List<TypePlatform> listTypes = typePlatformRepository.findAll();

        if(listTypes.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TypePlatformDto> listReturn = new ArrayList<>();
        for (int i = 0; i < listTypes.size(); i++) {
            listReturn.add(new TypePlatformDto(listTypes.get(i) ));
        }
        return listReturn;
    }

    public List<TypeStateDto> getState(){
        List<TypeState> listTypes = typeStateRepository.findAll();

        if(listTypes.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TypeStateDto> listReturn = new ArrayList<>();
        for (int i = 0; i < listTypes.size(); i++) {
            listReturn.add(new TypeStateDto(listTypes.get(i) ));
        }
        return listReturn;
    }

    public List<TypeStatusOrderDto> getStatusOrder(){
        List<TypeStatusOrder> listTypes = typeStatusOrderRepository.findAll();

        if(listTypes.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TypeStatusOrderDto> listReturn = new ArrayList<>();
        for (int i = 0; i < listTypes.size(); i++) {
            listReturn.add(new TypeStatusOrderDto(listTypes.get(i) ));
        }
        return listReturn;
    }

    public List<TypeWarehouseMovementDto> getWarehouseMovement() {
        List<TypeWarehouseMovement> listTypes = typeWarehouseMovementRepository.findAll();

        if(listTypes.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TypeWarehouseMovementDto> listReturn = new ArrayList<>();
        for (int i = 0; i < listTypes.size(); i++) {
            listReturn.add(new TypeWarehouseMovementDto(listTypes.get(i) ));
        }
        return listReturn;
    }

}
