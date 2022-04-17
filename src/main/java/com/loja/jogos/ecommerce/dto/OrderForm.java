package com.loja.jogos.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderForm {

    private Long id;
    private Long idPrice;
    private Long idCustomer;
    private Long idTypeStatusOrder;
    private Long quantity;
    private String trackingCode;

}


