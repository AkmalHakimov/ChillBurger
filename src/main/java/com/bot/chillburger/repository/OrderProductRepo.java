package com.bot.chillburger.repository;

import com.bot.chillburger.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct,Integer> {

    OrderProduct getOrderProductByProductId(Integer productId);

    @Query(value = "select count(*) from order_product where telegram_user_id = :telegramUserId",nativeQuery = true)
    Integer countAll(UUID telegramUserId);

    @Query(value = "select * from order_product where order_id is null and telegram_user_id = :telegramUserId",nativeQuery = true)
    List<OrderProduct> getOrderIdNullOrdersItems(UUID telegramUserId);
//
//    @Query(value = "select sum(amount) from order_product where telegram_user_id = :telegramUserId",nativeQuery = true)
//    Integer calculateTotal(UUID telegramUserId);

}
