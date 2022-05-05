package com.loja.jogos.ecommerce.service;

import com.loja.jogos.ecommerce.dto.*;
import com.loja.jogos.ecommerce.entity.*;
import com.loja.jogos.ecommerce.repository.*;
import com.loja.jogos.ecommerce.repository.specifications.OrderSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final CustomerRepository customerRepository;

    private final GamePlatformRepository gamePlatformRepository;

    private final GameRepository gameRepository;

    private final ItenRepository itenRepository;

    private final OrderRepository orderRepository;

    private final PriceRepository priceRepository;

    private final TypeStatusOrderRepository typeStatusOrderRepository;


    public Page<Order> findAll(Pageable pageable) {
        return  this.orderRepository.findAll(pageable);
    }

    public Page<Order> findAll(Pageable pageable, String query) {
        return this.orderRepository.findAll(OrderSpecification.likeGenericQuery(query), pageable);
    }

    public Page<Order> findAll(Pageable pageable, Long orderStatus, Long idOrder, Long idCustomer, String username, String firstName,String lastName,String email,String cpf) {
        return this.orderRepository.findAll(OrderSpecification.likeDescription(orderStatus, idOrder, idCustomer, username, firstName, lastName, email, cpf), pageable);
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

    public OrderDto fillCustomerInfo(OrderDto orderDto){
        Customer customer = customerRepository.findById(orderDto.getIdCustomer()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderDto.setFirstName(customer.getFirstName());
        orderDto.setLastName(customer.getLastName());
        orderDto.setEmail(customer.getEmail());
        return orderDto;
    }

    public ItenDto fillItenInfo(ItenDto itenDto){
        Price price = priceRepository.findById(itenDto.getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price not found"));;
        itenDto.setUnityValue(price.getValue());
        itenDto.setSubTotal(itenDto.getUnityValue()*itenDto.getQuantity());
        GamePlatform gamePlatform = gamePlatformRepository.findById(price.getGamePlatform()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"GamePlatform not found"));;
        itenDto.setTypePlatformId(gamePlatform.getPlatform().getId());
        Long gameId = gamePlatform.getGame().getId();
        itenDto.setGameId(gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
        itenDto.setGameName(game.getName());
        itenDto.setGameCover(game.getCover());
        return itenDto;
    }

    public OrderWrapperDto findOrderProfileById(Long id){
        OrderWrapperDto response = new OrderWrapperDto();
        Order order = orderRepository.findOrderById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        OrderDto orderDto = new OrderDto(order);
        response.setOrder(this.fillCustomerInfo(orderDto));
        List<Iten> itens = itenRepository.findByOrder(order);
        List<ItenDto> itensDto = new ArrayList<>();
        for(int i=0;i<itens.size();i++) {
            ItenDto newItenDto = new ItenDto(itens.get(i));
            this.fillItenInfo(newItenDto);
            itensDto.add(newItenDto);
        }
        response.setItens(itensDto);
        return  response;
    }

   public Page<OrderDto> fillCustomerPageInfo(Page<OrderDto> pageReturnObject) {
        for(int i =0; i<pageReturnObject.getContent().size();i++){
            // Ao que indica, o pageReturnObject é estático
            fillCustomerInfo(pageReturnObject.getContent().get(i));
        }
        return pageReturnObject;
    }
}
