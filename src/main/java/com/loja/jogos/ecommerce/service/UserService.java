package com.loja.jogos.ecommerce.service;


import com.loja.jogos.ecommerce.dto.UserDto;
import com.loja.jogos.ecommerce.dto.UserForm;
import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.repository.UserRepository;
import com.loja.jogos.ecommerce.repository.specifications.UserSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final CheckJwtInfoService checkJwtInfoService;


    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(UserSpecification.isActive(), pageable);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserDto(user);
    }

    public String encodePassword(String notEncodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  encoder.encode(notEncodedPassword);

    }


    public void createUser(UserForm form) {
        Optional<User> otherUser = userRepository.findByEmail(form.getEmail());
        if(otherUser.isPresent())  throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "EMAIL");
        if(form.getPassword()==null)  throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "PASSWORD");
        String encodePassword = this.encodePassword(form.getPassword());
        form.setPassword(encodePassword);
        User newUser = new User(form);
        userRepository.save(newUser);

    }

    public void inactivateUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setIsActive(false);
        userRepository.save(user);
    }

    public void updateUserByAdmin(Long id, UserForm form) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.update(form);
        userRepository.save(user);
    }

    public void updateUserByUser(UserForm form,  String token) {
        User user = checkJwtInfoService.getUser(token);
        user.selfUpdate(form);
        userRepository.save(user);
    }

    public void updateUserPassword(UserForm form,  String token) {
        User user = checkJwtInfoService.getUser(token);
        if(form.getPassword()!=null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            form.setPassword(encoder.encode(form.getPassword()));
        }
        user.updatePassword(form);
        userRepository.save(user);
    }

    public Boolean  testAdmin(String token){
        Boolean dto = checkJwtInfoService.testAdmin(token);
        return dto;
    }

    public Boolean testActive(String token) {
        Boolean dto = checkJwtInfoService.testActive(token);
        return dto;
    }

    public UserDto getSelfInfo(String token) {
        User user = checkJwtInfoService.getUser(token);
        return new UserDto(user);
    }
}
