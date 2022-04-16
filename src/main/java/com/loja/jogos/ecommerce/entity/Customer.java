package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.CustomerForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblcustomer")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcustomer")
    private Long id;

    @Column(name = "dsusername")
    private String username;

    @Column(name = "dsfirstname")
    private String firstName;

    @Column(name = "dslastname")
    private String lastName;

    @Column(name = "dsemail")
    private String email;

    @Column(name = "dsbirthdate")
    private LocalDate birthDate;

    @Column(name = "dscpf")
    private String cpf;

    @Column(name = "dsstreet")
    private String street;

    @Column(name = "dsnumber")
    private String number;

    @Column(name = "dscity")
    private String city;

    @Column(name = "idtypestate")
    private Long stateId;

    @Column(name = "dszip")
    private String zip;

    @Column(name = "stactive")
    private Boolean isActive;

    public Customer(CustomerForm form) {
        this.username = form.getUsername();
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
        this.username = form.getUsername();
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

