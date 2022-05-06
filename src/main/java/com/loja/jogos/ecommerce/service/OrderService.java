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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {

    private final CustomerRepository customerRepository;

    private final GamePlatformRepository gamePlatformRepository;

    private final GameRepository gameRepository;

    private final ItemRepository itemRepository;

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

    public void  createOrder(OrderWrapperForm form) {
        // Validar id de usuário
        Long customerId = 1L; // Atualizar para Id do token
        // Validar quantidade de itens
        if(form.getItens().size()<1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No itens found");
        // Validar as ids de preço
        List<Price> priceList = new ArrayList<>();
        for(int i=0;i<form.getItens().size();i++) {
            Price price = priceRepository.findById(form.getItens().get(i).getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid price ID"));
            priceList.add(price);
        }
        // Criar ordem
        Customer customer = customerRepository.findCustomerById(customerId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid User"));
        TypeStatusOrder typeStatusOrder = typeStatusOrderRepository.findById(1L).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid TypeStatusOrder ID"));
        Order newOrder = new Order(customer, typeStatusOrder);
        orderRepository.save(newOrder);
        Double totalOrder = 0D;
        List<Item> itens = new ArrayList<>();
        for(int i=0;i<form.getItens().size();i++) {
            Item newItem = new Item(newOrder, form.getItens().get(i));
            totalOrder = totalOrder + (priceList.get(i).getValue() * newItem.getQuantity());
            itemRepository.save(newItem);
        }
        newOrder.setTotalValue(totalOrder);
        orderRepository.save(newOrder);
    }

    public void  reviewOrderTotalValueById(Long id) {
        Order order = orderRepository.findOrderById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        List<Item> itens = itemRepository.findByOrder(order);
        List<Price> priceList = new ArrayList<>();
        for(int i=0;i<itens.size();i++) {
            Price price = priceRepository.findById(itens.get(i).getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid price ID"));
            priceList.add(price);
        }
        Double totalOrder = 0D;
        for(int i=0;i<itens.size();i++) {
            totalOrder = totalOrder + (priceList.get(i).getValue() * itens.get(i).getQuantity());
        }
        order.setTotalValue(totalOrder);
        orderRepository.save(order);
    }

    public void  reviewAllOrdersValue() {
        List<Order> orderList = orderRepository.findAll();
        for(Order order: orderList){
            List<Item> itens = itemRepository.findByOrder(order);
            List<Price> priceList = new ArrayList<>();
            for(int i=0;i<itens.size();i++) {
                Price price = priceRepository.findById(itens.get(i).getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid price ID"));
                priceList.add(price);
            }
            Double totalOrder = 0D;
            for(int i=0;i<itens.size();i++) {
                totalOrder = totalOrder + (priceList.get(i).getValue() * itens.get(i).getQuantity());
            }
            order.setTotalValue(totalOrder);
            orderRepository.save(order);
        }
    }

    public void  cancelNotPaidOneWeekOldOrders() {
        List<Order> orderList = orderRepository.findAll();
        LocalDateTime thresholdDateTimeOrder = LocalDateTime.now();
        thresholdDateTimeOrder.minusWeeks(1); // Limiar para cancelamento de compras é de 1 semana até a data de hoje
        Optional<TypeStatusOrder> canceledTypeStatusOrder = typeStatusOrderRepository.findById(6L);

        for(Order order: orderList){
            if(order.getDateTimeOrder().isBefore(thresholdDateTimeOrder) && order.getTypeStatusOrder().getId()==1L ){
                order.setTypeStatusOrder(canceledTypeStatusOrder.get());
                orderRepository.save(order);
            }
        }
    }


    public void  reviewOrderTotalValue(Order order) {
        List<Item> itens = itemRepository.findByOrder(order);
        List<Price> priceList = new ArrayList<>();
        for(int i=0;i<itens.size();i++) {
            Price price = priceRepository.findById(itens.get(i).getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid price ID"));
            priceList.add(price);
        }
        Double totalOrder = 0D;
        for(int i=0;i<itens.size();i++) {
            totalOrder = totalOrder + (priceList.get(i).getValue() * itens.get(i).getQuantity());
        }
        order.setTotalValue(totalOrder);
        orderRepository.save(order);
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

    public ItemDto fillItemInfo(ItemDto itemDto){
        Price price = priceRepository.findById(itemDto.getIdPrice()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price not found"));;
        itemDto.setUnityValue(price.getValue());
        itemDto.setSubTotal(itemDto.getUnityValue()* itemDto.getQuantity());
        GamePlatform gamePlatform = gamePlatformRepository.findById(price.getGamePlatform()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"GamePlatform not found"));;
        itemDto.setTypePlatformId(gamePlatform.getPlatform().getId());
        Long gameId = gamePlatform.getGame().getId();
        itemDto.setGameId(gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game not found"));
        itemDto.setGameName(game.getName());
        itemDto.setGameCover(game.getCover());
        return itemDto;
    }

    public OrderWrapperDto findOrderProfileById(Long id){
        OrderWrapperDto response = new OrderWrapperDto();
        Order order = orderRepository.findOrderById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        OrderDto orderDto = new OrderDto(order);
        response.setOrder(this.fillCustomerInfo(orderDto));
        List<Item> itens = itemRepository.findByOrder(order);
        List<ItemDto> itensDto = new ArrayList<>();
        for(int i=0;i<itens.size();i++) {
            ItemDto newItemDto = new ItemDto(itens.get(i));
            this.fillItemInfo(newItemDto);
            itensDto.add(newItemDto);
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
