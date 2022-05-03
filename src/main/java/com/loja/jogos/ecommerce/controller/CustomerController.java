package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.CustomerDto;
import com.loja.jogos.ecommerce.dto.CustomerForm;
import com.loja.jogos.ecommerce.dto.GameDto;
import com.loja.jogos.ecommerce.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/customer")
@AllArgsConstructor
public class CustomerController {

    private final ConversionService conversionService;

    private final CustomerService customerService;


    @GetMapping
    public @ResponseBody
    Page<CustomerDto> findAllPaginated(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) Long state,
            Pageable pageable
    ){
        if(query==null && firstName==null && lastName==null && email==null && cpf==null && state==null) { // Busca sem nenhum parametro
            Page<CustomerDto> pageReturnObject = this.customerService
                    .findAll(pageable)
                    .map(entity -> this.conversionService.convert(entity, CustomerDto.class));

        } else if(query!=null && firstName==null && lastName==null && email==null && cpf==null && state==null ){ // Busca com uma query generica
            Page<CustomerDto> pageReturnObject = this.customerService
                    .findAll(pageable,query)
                    .map(entity -> this.conversionService.convert(entity,CustomerDto.class));
        }
        Page<CustomerDto> pageReturnObject = this.customerService // Busca com dados de perfil
                .findAll(pageable,firstName, lastName, email, cpf, state)
                .map(entity -> this.conversionService.convert(entity,CustomerDto.class));
        return pageReturnObject;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        CustomerDto response = this.customerService.findCustomerByOd(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerForm form) {
        this.customerService.createCustomer(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inactiveCustomer(@PathVariable Long id) {
        this.customerService.inactiveCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody  CustomerForm form) {
        this.customerService.updateCustomer(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
