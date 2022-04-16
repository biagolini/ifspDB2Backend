package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Customer;
import com.loja.jogos.ecommerce.entity.Customer_;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {


    public static Specification<Customer> userNameLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.username),"%"+info+"%");
    }

    public static Specification<Customer> firstNameLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.firstName),"%"+info+"%");
    }

    public static Specification<Customer> lastNameLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.lastName),"%"+info+"%");
    }

    public static Specification<Customer> emailLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.email),"%"+info+"%");
    }

    public static Specification<Customer> cpfLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.cpf),"%"+info+"%");
    }

    public static Specification<Customer> cityLike(String info){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Customer_.city),"%"+info+"%");
    }

    public static Specification<Customer> isActive(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(Customer_.isActive));
    }


    public static Specification<Customer> likeDescription(String firstName, String lastName, String email, String cpf){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(5);
            if(firstName != null) predicates.add(firstNameLike(firstName).toPredicate(root,query,criteriaBuilder));
            if(lastName != null) predicates.add(lastNameLike(lastName).toPredicate(root,query,criteriaBuilder));
            if(email != null) predicates.add(emailLike(email).toPredicate(root,query,criteriaBuilder));
            if(cpf != null) predicates.add(cpfLike(cpf).toPredicate(root,query,criteriaBuilder));
            predicates.add(criteriaBuilder.isTrue(root.get(Customer_.isActive)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Customer> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(6);
            predicates.add(userNameLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(firstNameLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(lastNameLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(emailLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(cpfLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(cityLike(queryString).toPredicate(root, query, criteriaBuilder));
            Predicate ors = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            Predicate active = criteriaBuilder.isTrue(root.get(Customer_.isActive));
            return criteriaBuilder.and(ors, active);
        };
    }

}
