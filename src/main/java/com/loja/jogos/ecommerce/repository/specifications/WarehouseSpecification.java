package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import com.loja.jogos.ecommerce.entity.WarehouseExit;
import com.loja.jogos.ecommerce.entity.*;
import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {

    // WarehouseBalance - gamePlatform
    public static Specification<WarehouseBalance>  balanceGamePlatformLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(WarehouseBalance_.gamePlatform).get(GamePlatform_.game).get(Game_.NAME) ,  "%"+info+"%" );
    }

    // WarehouseEntrance - gamePlatform
    public static Specification<WarehouseEntrance>  entranceGamePlatformLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(WarehouseEntrance_.gamePlatform).get(GamePlatform_.game).get(Game_.NAME) ,  "%"+info+"%" );
    }

    // WarehouseExit - gamePlatform
    public static Specification<WarehouseExit>  exitGamePlatformLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(WarehouseExit_.gamePlatform).get(GamePlatform_.game).get(Game_.NAME) ,  "%"+info+"%" );
    }

}
