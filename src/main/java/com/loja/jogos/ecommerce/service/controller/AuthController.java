package com.loja.jogos.ecommerce.service.controller;

import com.loja.jogos.ecommerce.dto.LoginForm;
import com.loja.jogos.ecommerce.dto.TokenDto;
import com.loja.jogos.ecommerce.dto.UserDescriptionDto;
import com.loja.jogos.ecommerce.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            UserDescriptionDto description = tokenService.getUserDescription(authentication);
            return ResponseEntity.ok(new TokenDto(token,"Bearer", description.getScope(),description.getName()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

