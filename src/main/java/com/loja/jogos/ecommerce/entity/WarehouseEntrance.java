package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblwarehouseentrance")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntrance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwarehouseentrance")
    private Long id;

    @Column(name = "idgameplataform")
    private Long gamePlataform;

    @Column(name = "dsquantity")
    private Long quantity;

    @Column(name = "stactive")
    private Boolean isActive;

}
