package com.bot.chillburger.repository;

import com.bot.chillburger.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findByEngNameContainingOrUzNameContaining(String name,String x);
}
