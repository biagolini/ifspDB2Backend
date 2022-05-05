package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.ItemForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblItem")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long id;

    @JoinColumn(name = "idOrder")
    @ManyToOne
    private Order order;

    @Column(name = "idPrice")
    private Long idPrice;

    @Column(name = "dsQuantity")
    private Integer quantity;


    public Item(Order order, ItemForm itemForm) {
        this.order = order;
        this.idPrice = itemForm.getIdPrice();
        this.quantity = itemForm.getQuantity();
    }
}
