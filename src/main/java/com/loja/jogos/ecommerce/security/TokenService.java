package com.loja.jogos.ecommerce.security;


import com.loja.jogos.ecommerce.dto.UserDescriptionDto;
import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.entity.UserProfile;
import com.loja.jogos.ecommerce.repository.UserProfileRepository;
import com.loja.jogos.ecommerce.service.CheckJwtInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
public class TokenService {

    private final UserProfileRepository userProfileRepository;

    private final CheckJwtInfoService checkJwtInfoService;

    @Value("${appParameters.jwt.secret}")
    private String secret;

    @Value("${appParameters.jwt.expiration}")
    private String expiration;

    public UserDescriptionDto getUserDescription(Authentication authentication) {
        UserDescriptionDto descriptionDto = new UserDescriptionDto();
        User user = (User) authentication.getPrincipal();
        UserProfile userProfile = userProfileRepository.findById(user.getIdUserProfile()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR USER PROFILE NOT FOUND"));
        descriptionDto.setScope(userProfile.getDescription().toLowerCase());
        descriptionDto.setName(user.getName());
        return descriptionDto;
    }


    public String gerarToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        LocalDateTime todayLD = LocalDateTime.now();
        LocalDateTime expLD = todayLD.plusDays(Integer.parseInt(expiration));
        Date todayDate = java.util.Date.from(todayLD.atZone(ZoneId.systemDefault()).toInstant());
        Date expDate = java.util.Date.from(expLD.atZone(ZoneId.systemDefault()).toInstant());
        UserProfile userProfile = userProfileRepository.findById(user.getIdUserProfile()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR USER PROFILE NOT FOUND"));
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("scope", userProfile.getDescription().toLowerCase())
                .claim("name", user.getName())
                .setIssuedAt(todayDate)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            checkJwtInfoService.blockOldToken(token);
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public TokenService(UserProfileRepository userProfileRepository, CheckJwtInfoService checkJwtInfoService) {
        this.userProfileRepository = userProfileRepository;
        this.checkJwtInfoService = checkJwtInfoService;

    }
}