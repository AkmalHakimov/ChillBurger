package com.bot.chillburger.repository;

import com.bot.chillburger.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findByRuNameContainingOrUzNameContaining(String name, String x);
}
