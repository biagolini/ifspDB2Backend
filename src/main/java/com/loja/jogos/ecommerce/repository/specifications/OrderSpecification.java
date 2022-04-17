package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Customer_;
import com.loja.jogos.ecommerce.entity.Order;
import com.loja.jogos.ecommerce.entity.Order_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order>  userIdLike(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.customer).get(Customer_.id),  info );
    }

    public static Specification<Order> userNameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.USERNAME),  info );
    }


    public static Specification<Order> userCpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.CPF),  info );
    }

    public static Specification<Order> likeDescription(Long id, String username, String cpf) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(3);
            if (id != null) predicates.add(userIdLike(id).toPredicate(root, query, criteriaBuilder));
            if (username != null) predicates.add(userNameLike(username).toPredicate(root, query, criteriaBuilder));
            if (cpf != null) predicates.add(userCpfLike(cpf).toPredicate(root, query, criteriaBuilder));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
