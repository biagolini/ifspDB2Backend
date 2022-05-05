package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.CustomerDto;
import com.loja.jogos.ecommerce.dto.OrderDto;
import com.loja.jogos.ecommerce.dto.OrderForm;
import com.loja.jogos.ecommerce.dto.OrderWrapperDto;
import com.loja.jogos.ecommerce.service.OrderService;
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
@RequestMapping("api/order")
@AllArgsConstructor
public class OderController {

    private final ConversionService conversionService;

    private final OrderService orderService;

    @GetMapping
    public @ResponseBody
    Page<OrderDto> findAllPaginated(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long orderStatus,
            @RequestParam(required = false) Long idOrder,
            @RequestParam(required = false) Long idCustomer,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf,
            @PageableDefault(sort = "dateTimeOrder", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        if (query == null && idCustomer == null && orderStatus == null &&  idOrder == null &&  idCustomer == null &&  username == null &&  firstName == null
                &&  lastName == null &&  email == null &&  cpf == null  ) { // Busca sem nenhum parametro
            Page<OrderDto> pageReturnObject = this.orderService.findAll(pageable).map(entity -> this.conversionService.convert(entity, OrderDto.class));
            return  this.orderService.fillCustomerPageInfo(pageReturnObject);
        }
        if (idCustomer != null || orderStatus != null ||  idOrder != null ||  idCustomer != null ||  username != null ||  firstName != null
                ||  lastName != null ||  email != null ||  cpf != null  ) { // Tem pelo menos 1 parametro de busca
            Page<OrderDto> pageReturnObject = this.orderService // Busca com dados de perfil
                    .findAll(pageable, orderStatus, idOrder, idCustomer, username, firstName, lastName, email, cpf)
                    .map(entity -> this.conversionService.convert(entity, OrderDto.class));
                    return this.orderService.fillCustomerPageInfo(pageReturnObject);
        }
        Page<OrderDto> pageReturnObject = this.orderService.findAll(pageable,query).map(entity -> this.conversionService.convert(entity, OrderDto.class));
        return  this.orderService.fillCustomerPageInfo(pageReturnObject);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderForm form) {
        this.orderService.createOrder(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> UpdateStatus(@PathVariable Long id, @RequestBody  OrderForm form) {
        this.orderService.UpdateStatus(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orderProfile/{id}")
    public ResponseEntity<?> findOrderProfileById(@PathVariable Long id) {
        OrderWrapperDto response = this.orderService.findOrderProfileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
