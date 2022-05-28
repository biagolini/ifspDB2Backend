package com.loja.jogos.ecommerce.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDeFormularioDto {

    private String campo;
    private String erro;

}
