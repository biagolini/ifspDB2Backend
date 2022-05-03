package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblIten")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Iten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIten")
    private Long id;

    @JoinColumn(name = "idOrder")
    @ManyToOne
    private Order order;

    @Column(name = "idPrice")
    private Long idPrice;

    @Column(name = "dsQuantity")
    private Integer quantity;

}
