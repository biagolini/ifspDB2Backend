package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Customer_;
import com.loja.jogos.ecommerce.entity.Order;
import com.loja.jogos.ecommerce.entity.Order_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order>  userIdEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.customer).get(Customer_.id),  info );
    }

    public static Specification<Order> userNameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.USERNAME),  "%"+info +"%");
    }

    public static Specification<Order> userNameEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.USERNAME),  info );
    }

    public static Specification<Order> userCpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.CPF),  "%"+info +"%");
    }

    public static Specification<Order> userCpfEquals(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.customer).get(Customer_.CPF),  info );
    }

    public static Specification<Order> likeDescription(Long id, String username, String cpf, String queryString) {
        return (root, query, criteriaBuilder) -> {
            // ANDS
            List<Predicate> predicatesAND = new ArrayList<>(3);
            if (id != null) predicatesAND.add(userIdEquals(id).toPredicate(root, query, criteriaBuilder));
            if (username != null) predicatesAND.add(userNameEquals(username).toPredicate(root, query, criteriaBuilder));
            if (cpf != null) predicatesAND.add(userCpfEquals(cpf).toPredicate(root, query, criteriaBuilder));
            Predicate ands = criteriaBuilder.and(predicatesAND.toArray(new Predicate[0]));
            // ORS
            List<Predicate> predicatesOR = new ArrayList<>(2);
            predicatesOR.add(userNameLike(queryString).toPredicate(root,query,criteriaBuilder));
            predicatesOR.add(userCpfLike(queryString).toPredicate(root,query,criteriaBuilder));
            Predicate ors = criteriaBuilder.or(predicatesOR.toArray(new Predicate[0]));

            return criteriaBuilder.and(ands,ors);
        };
    }
}
