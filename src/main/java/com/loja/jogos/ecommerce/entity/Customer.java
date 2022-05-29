package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.CustomerForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblCustomer")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer")
    private Long id;

    @Column(name = "dsFirstName")
    private String firstName;

    @Column(name = "dsLastName")
    private String lastName;

    @Column(name = "dsEmail")
    private String email;

    @Column(name = "dsBirthDate")
    private LocalDate birthDate;

    @Column(name = "dsCPF")
    private String cpf;

    @Column(name = "dsStreet")
    private String street;

    @Column(name = "dsNumber")
    private String number;

    @Column(name = "dsCity")
    private String city;

    @Column(name = "idTypeState")
    private Long stateId;

    @Column(name = "dsZip")
    private String zip;

    @Column(name = "stActive")
    private Boolean isActive;

    public Customer(CustomerForm form) {
        this.firstName = form.getFirstName();
        this.lastName = form.getLastName();
        this.email = form.getEmail();
        this.birthDate = form.getBirthDate();
        this.cpf = form.getCpf();
        this.street = form.getStreet();
        this.number = form.getNumber();
        this.city = form.getCity();
        this.stateId = form.getState();
        this.zip = form.getZip();
        this.isActive = true;
    }

    public void update(CustomerForm form) {
        this.firstName = form.getFirstName();
        this.lastName = form.getLastName();
        this.email = form.getEmail();
        this.birthDate = form.getBirthDate();
        this.cpf = form.getCpf();
        this.street = form.getStreet();
        this.number = form.getNumber();
        this.city = form.getCity();
        this.stateId = form.getState();
        this.zip = form.getZip();
        this.isActive = true;
    }

}

