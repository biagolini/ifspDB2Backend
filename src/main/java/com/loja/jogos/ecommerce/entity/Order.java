package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.OrderForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblOrder")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private Long id;

    @JoinColumn(name = "idCustomer")
    @ManyToOne
    private Customer customer;

    @JoinColumn(name = "idTypeStatusOrder")
    @ManyToOne
    private TypeStatusOrder typeStatusOrder;

    @Column(name = "dsDateTimeOrder")
    private LocalDateTime dateTimeOrder;

    @Column(name = "dsTotalValue")
    private Double totalValue;

    @Column(name = "dsTrackingCode")
    private String trackingCode;

    public Order(OrderForm form, Customer customer, TypeStatusOrder typeStatusOrder) {
        this.customer = customer;
        this.typeStatusOrder = typeStatusOrder;
        this.dateTimeOrder = LocalDateTime.now();
        this.trackingCode = form.getTrackingCode();
    }

    public void updateStatus(OrderForm form, TypeStatusOrder typeStatusOrder) {
        this.typeStatusOrder = typeStatusOrder;
    }

}

