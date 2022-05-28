package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.PriceForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblPrice")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrice")
    private Long id;

    @Column(name = "idGamePlatform")
    private Long gamePlatform;

    @Column(name = "dsValue")
    private double value;

    @Column(name = "dsDateTimePublish")
    private LocalDateTime dateTimePublish;

    @Column(name = "stActive")
    private Boolean isActive;

    public Price(PriceForm form) {
        this.gamePlatform = form.getIdGamePlatform();
        this.value = form.getValue();
        this.dateTimePublish = LocalDateTime.now();
        this.isActive = true;
    }
}

