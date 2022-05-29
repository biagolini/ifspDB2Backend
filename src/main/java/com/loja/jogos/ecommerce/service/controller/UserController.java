package com.loja.jogos.ecommerce.service.controller;

import com.loja.jogos.ecommerce.dto.UserDto;
import com.loja.jogos.ecommerce.dto.UserForm;
import com.loja.jogos.ecommerce.service.CheckJwtInfoService;
import com.loja.jogos.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private final ConversionService conversionService;

    private final CheckJwtInfoService checkJwtInfoService;

    @GetMapping("/allUsers")
    public @ResponseBody
    Page<UserDto> findAllPaginated(
            @PageableDefault(sort = "name", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
            @RequestHeader (name="Authorization") String token
    ){
        this.checkJwtInfoService.blockNotAdmin(token);
        return this.userService.findAll(pageable).map(entity -> this.conversionService.convert(entity, UserDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id,@RequestHeader (name="Authorization") String token){
        this.checkJwtInfoService.blockNotAdmin(token);
        UserDto dto = this.userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/selfInfo")
    public ResponseEntity<?> getSelfInfo(@RequestHeader (name="Authorization") String token){
        UserDto dto = this.userService.getSelfInfo(token);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<?> testAdmin(@RequestHeader (name="Authorization") String token){
        Boolean dto = this.userService.testAdmin(token);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/isActive")
    public ResponseEntity<?> testActive(@RequestHeader (name="Authorization") String token){
        Boolean dto = this.userService.testActive(token);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserForm form, @RequestHeader (name="Authorization") String token){
        this.checkJwtInfoService.blockNotAdmin(token);
        this.userService.createUser(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/password")
    @Transactional
    public ResponseEntity<?> updateUserPassword(@RequestBody UserForm form, @RequestHeader (name="Authorization") String token){
        this.userService.updateUserPassword(form,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/self")
    @Transactional
    public ResponseEntity<?> updateUserByUser(@RequestBody UserForm form, @RequestHeader (name="Authorization") String token){
        this.userService.updateUserByUser(form,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateUserByAdmin(@PathVariable Long id, @RequestBody UserForm form, @RequestHeader (name="Authorization") String token){
        this.checkJwtInfoService.blockNotAdmin(token);
        this.userService.updateUserByAdmin(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> inactivateUserById(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        this.checkJwtInfoService.blockNotAdmin(token);
        this.userService.inactivateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
