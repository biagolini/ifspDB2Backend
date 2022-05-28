package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.entity.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> isActive(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(User_.isActive));
    }

}
