package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Long idCustomer;
    private String firstName;
    private String lastName;
    private String email;
    private Long idTypeStatusOrder;
    private LocalDateTime dateTimeOrder;
    private Double totalValue;
    private String trackingCode;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.idCustomer = order.getCustomer().getId();
        this.idTypeStatusOrder = order.getTypeStatusOrder().getId();
        this.dateTimeOrder = order.getDateTimeOrder();
        this.totalValue = order.getTotalValue();
        this.trackingCode = order.getTrackingCode();
    }
}

