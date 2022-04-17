package com.loja.jogos.ecommerce.controller;

import com.loja.jogos.ecommerce.dto.OrderDto;
import com.loja.jogos.ecommerce.dto.OrderForm;
import com.loja.jogos.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String cpf,
            Pageable pageable
    ) {
        if (id == null && username == null && cpf == null) { // Busca sem nenhum parametro
            return this.orderService.findAll(pageable).map(entity -> this.conversionService.convert(entity, OrderDto.class));
        }
            return this.orderService // Busca com dados de perfil
                    .findByDescription(pageable, id, username, cpf)
                    .map(entity -> this.conversionService.convert(entity, OrderDto.class));
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


}
