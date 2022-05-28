package com.loja.jogos.ecommerce.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class CheckJwtInfoService {

    private final UserRepository userRepository;


    public String getPayloadItem(String token, String item) {
        String dto = "INVALID";
        token = token.replaceFirst("Bearer", "").trim();
        String[] infos = token.split(Pattern.quote("."));
        Base64.Decoder dec = Base64.getDecoder();
        try {
            String tokenBody = new String(Base64.getUrlDecoder().decode(infos[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(tokenBody, Map.class);
            dto = map.get(item).toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public Boolean testAdmin(String token) {
        String scope = this.getPayloadItem(token, "scope");
        return scope.trim().equals("admin");
    }

    public Boolean testActive(String token) {
        String idUserString = this.getPayloadItem(token, "sub");
        Optional<User> user = userRepository.findById(Long.parseLong(idUserString));
        return user.get().getIsActive();
    }

    public User getUser(String token) {
        String idUserString = this.getPayloadItem(token, "sub");
        User user = userRepository.findById(Long.parseLong(idUserString)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND"));
        return user;
    }

    public void blockNotAdmin(String token) {
        String scope = this.getPayloadItem(token, "scope");
        if (!scope.trim().equals("admin")) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not allowed");
    }


    public void blockOldToken(String token) {
        String idUserString = this.getPayloadItem(token, "sub");
        User user = userRepository.findById(Long.parseLong(idUserString)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDateTime lastPasswordupdate = user.getLastPasswordUpdate();
        String tokenIat = this.getPayloadItem(token, "iat");
        Date tokenDate = new Date(Long.parseLong(tokenIat) * 1000L);
        LocalDateTime tokenDateTime = tokenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Boolean tokenValidityTest = tokenDateTime.isAfter(lastPasswordupdate);
        if (!tokenValidityTest) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}