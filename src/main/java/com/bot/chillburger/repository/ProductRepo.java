package com.bot.chillburger.repository;

import com.bot.chillburger.entity.Category;
import com.bot.chillburger.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findAllByCategoryId(Integer id);

    Product findByEngNameContainingOrUzNameContaining(String text, String text1);
}
