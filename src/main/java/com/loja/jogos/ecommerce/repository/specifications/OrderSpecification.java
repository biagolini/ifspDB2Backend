package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Order;
import com.loja.jogos.ecommerce.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    // orderStatus
    public static Specification<Order>  orderStatusEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.typeStatusOrder).get(TypeStatusOrder_.id) ,  info );
    }

    // idOrder
    public static Specification<Order>  orderIdEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.id),  info );
    }

    //idCustomer
    public static Specification<Order>  customerIdEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.customer).get(Customer_.id),  info );
    }

    //firstName
    public static Specification<Order>  customerFirstNameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.FIRST_NAME),  "%"+info +"%");
    }

    public static Specification<Order>  customerFirstNameEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.FIRST_NAME),  info );
    }

    //lastName
    public static Specification<Order>  customerLastNameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.LAST_NAME),  "%"+info +"%");
    }

    public static Specification<Order>  customerLastNameEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.LAST_NAME),  info );
    }

    //email
    public static Specification<Order>  customerEmailLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.EMAIL),  info );
    }

    public static Specification<Order>  customerEmailEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.EMAIL),  info );
    }

    //cpf
    public static Specification<Order>  customerCpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.CPF),  "%"+info +"%");
    }

    public static Specification<Order> customerCpfEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.CPF),  info );
    }

    public static Specification<Order> likeDescription(Long orderStatus, Long idOrder, Long idCustomer,
                                                       String firstName, String lastName,
                                                       String email, String cpf) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesAND = new ArrayList<>(7);
            if (orderStatus != null) predicatesAND.add(orderStatusEquals(orderStatus).toPredicate(root, query, criteriaBuilder));
            if (idOrder != null) predicatesAND.add(orderIdEquals(idOrder).toPredicate(root, query, criteriaBuilder));
            if (idCustomer != null) predicatesAND.add(customerIdEquals(idCustomer).toPredicate(root, query, criteriaBuilder));
            if (firstName != null) predicatesAND.add(customerFirstNameEquals(firstName).toPredicate(root, query, criteriaBuilder));
            if (lastName != null) predicatesAND.add(customerLastNameEquals(lastName).toPredicate(root, query, criteriaBuilder));
            if (email != null) predicatesAND.add(customerEmailEquals(email).toPredicate(root, query, criteriaBuilder));
            if (cpf != null) predicatesAND.add(customerCpfEquals(cpf).toPredicate(root, query, criteriaBuilder));
            return  criteriaBuilder.and(predicatesAND.toArray(new Predicate[0]));
        };
    }

    public static Specification<Order> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesOR = new ArrayList<>(4);
            predicatesOR.add(customerFirstNameLike(queryString).toPredicate(root,query,criteriaBuilder));
            predicatesOR.add(customerLastNameLike(queryString).toPredicate(root,query,criteriaBuilder));
            predicatesOR.add(customerEmailLike(queryString).toPredicate(root,query,criteriaBuilder));
            predicatesOR.add(customerCpfLike(queryString).toPredicate(root,query,criteriaBuilder));
            return criteriaBuilder.or(predicatesOR.toArray(new Predicate[0]));
        };
    }

}

