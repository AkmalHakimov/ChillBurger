package com.bot.chillburger.repository;

import com.bot.chillburger.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepo extends JpaRepository<OrderProduct,Integer> {

}
