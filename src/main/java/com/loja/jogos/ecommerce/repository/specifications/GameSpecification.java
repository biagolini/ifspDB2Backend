package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Game;
import com.loja.jogos.ecommerce.entity.Game_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class GameSpecification {

    public static Specification<Game> nameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Game_.name), "%" + info + "%");
    }

    public static Specification<Game> genreLike(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Game_.genre), info);
    }

    public static Specification<Game> isActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(Game_.isActive));
    }

    public static Specification<Game> likeDescription(String name, Long genre) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(2);
            if (name != null) predicates.add(nameLike(name).toPredicate(root, query, criteriaBuilder));
            if (genre != null) predicates.add(genreLike(genre).toPredicate(root, query, criteriaBuilder));
            predicates.add(criteriaBuilder.isTrue(root.get(Game_.isActive)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}