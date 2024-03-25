package com.bot.chillburger.repository;

import com.bot.chillburger.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepo extends JpaRepository<OrderProduct,Integer> {

    OrderProduct getOrderProductByProductId(Integer productId);

    @Query(value = "select count(*) from order_product",nativeQuery = true)
    Integer countAll();

    @Query(value = "select * from order_product where order_id is null",nativeQuery = true)
    List<OrderProduct> getOrderIdNullOrdersItems();

    @Query(value = "select sum(amount) from order_product",nativeQuery = true)
    Integer calculateTotal();

}
