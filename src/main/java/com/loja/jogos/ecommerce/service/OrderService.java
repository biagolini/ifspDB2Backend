package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.OrderForm;
import com.loja.jogos.ecommerce.entity.Customer;
import com.loja.jogos.ecommerce.entity.Order;
import com.loja.jogos.ecommerce.entity.TypeStatusOrder;
import com.loja.jogos.ecommerce.repository.CustomerRepository;
import com.loja.jogos.ecommerce.repository.OrderRepository;
import com.loja.jogos.ecommerce.repository.TypeStatusOrderRepository;
import com.loja.jogos.ecommerce.repository.specifications.OrderSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class OrderService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final TypeStatusOrderRepository typeStatusOrderRepository;

    public Page<Order> findAll(Pageable pageable) {
        return  this.orderRepository.findAll(pageable);
    }

    public Page<Order> findByDescription(Pageable pageable, Long id, String username, String cpf) {
        return this.orderRepository.findAll(OrderSpecification.likeDescription(id, username, cpf), pageable);
    }

    public void  createOrder(OrderForm form) {
        Customer customer = customerRepository.findCustomerById(form.getIdCustomer()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid User"));
        TypeStatusOrder typeStatusOrder = typeStatusOrderRepository.findById(form.getIdTypeStatusOrder()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid TypeStatusOrder ID"));
        Order newRegister = new Order(form, customer, typeStatusOrder);
        orderRepository.save(newRegister);
    }

    public void  UpdateStatus(Long id, OrderForm form) {
        Order order = orderRepository.findOrderById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        TypeStatusOrder typeStatusOrder = typeStatusOrderRepository.findById(form.getIdTypeStatusOrder()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid TypeStatusOrder ID"));
        order.updateStatus(form, typeStatusOrder);
    }


}
