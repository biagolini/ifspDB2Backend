package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Long idUserProfile;
    private Boolean isActive;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.idUserProfile = user.getIdUserProfile();
        this.isActive = user.getIsActive();
    }
}
