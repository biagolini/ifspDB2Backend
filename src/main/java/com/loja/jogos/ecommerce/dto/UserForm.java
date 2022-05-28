package com.loja.jogos.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    private String name;
    private String email;
    private String password;
    private Long idUserProfile;
    private Long idCustomer;

}
