package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.Highlight;
import com.loja.jogos.ecommerce.entity.Highlight_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class HighlightSpecification {
    public static Specification<Highlight> isActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(Highlight_.isActive));
    }

    public static Specification<Highlight> newstHighlight() { // Não usado no projeto, mas mantive o codigo para fins didáticos
        return (root, query, criteriaBuilder) -> {
            // Predicate newst = criteriaBuilder.greatest(root.get(Highlight_.publicationDate)); // Pegar o maior
            query.orderBy(criteriaBuilder.desc(root.get(Highlight_.publicationDate)));
            Predicate active = criteriaBuilder.isTrue(root.get(Highlight_.isActive));
            return criteriaBuilder.and(active);
        };
    }

}
