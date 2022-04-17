package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.OrderForm;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "idPrice")
    private Long idPrice;

    @JoinColumn(name = "idCustomer")
    @ManyToOne
    private Customer customer;


    @JoinColumn(name = "idTypeStatusOrder")
    @ManyToOne
    private TypeStatusOrder typeStatusOrder;

    @Column(name = "dsQuantity")
    private Long quantity;

    @Column(name = "dsTrackingCode")
    private String trackingCode;

    public Order(OrderForm form, Customer customer, TypeStatusOrder typeStatusOrder) {
        this.idPrice = form.getIdPrice();
        this.customer = customer;
        this.typeStatusOrder = typeStatusOrder;
        this.quantity = form.getQuantity();
        this.trackingCode = form.getTrackingCode();
    }

    public void updateStatus(OrderForm form, TypeStatusOrder typeStatusOrder) {
        this.typeStatusOrder = typeStatusOrder;
    }

}

