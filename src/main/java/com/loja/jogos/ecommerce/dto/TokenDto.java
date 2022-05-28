package com.loja.jogos.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String tokenType;
    private String scope;
    private String name;


}
