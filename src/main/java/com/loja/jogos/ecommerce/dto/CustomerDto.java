package com.loja.jogos.ecommerce.dto;

import com.loja.jogos.ecommerce.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String cpf;
    private String street;
    private String number;
    private String city;
    private Long state;
    private String zip;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.birthDate = customer.getBirthDate();
        this.cpf = customer.getCpf();
        this.street = customer.getStreet();
        this.number = customer.getNumber();
        this.city = customer.getCity();
        this.state = customer.getStateId();
        this.zip = customer.getZip();
    }
}
