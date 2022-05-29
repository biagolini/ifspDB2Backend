package com.loja.jogos.ecommerce.repository.specifications;

import com.loja.jogos.ecommerce.entity.WarehouseBalance;
import com.loja.jogos.ecommerce.entity.WarehouseEntrance;
import com.loja.jogos.ecommerce.entity.WarehouseExit;
import com.loja.jogos.ecommerce.entity.*;
import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {

    // WarehouseBalance - gamePlatform
    public static Specification<WarehouseBalance>  balanceGamePlatformEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(WarehouseBalance_.gamePlatform).get(GamePlatform_.game).get(Game_.id) ,  info );
    }

    // WarehouseEntrance - gamePlatform
    public static Specification<WarehouseEntrance>  entranceGamePlatformEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(WarehouseEntrance_.gamePlatform).get(GamePlatform_.game).get(Game_.id) ,  info );
    }

    // WarehouseExit - gamePlatform
    public static Specification<WarehouseExit>  exitGamePlatformEquals(Long info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(WarehouseExit_.gamePlatform).get(GamePlatform_.game).get(Game_.id) ,  info );
    }

}
