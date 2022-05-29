package com.loja.jogos.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblWarehouseBalance")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWarehouseBalance")
    private Long id;

    @JoinColumn(name = "idGamePlatform")
    @ManyToOne
    private GamePlatform gamePlatform;

    @Column(name = "dsQuantity")
    private Long quantity;

    @Column(name = "dsLastUpdate")
    private LocalDateTime lastUpdate;

}
