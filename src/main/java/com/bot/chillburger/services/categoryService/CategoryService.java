package com.bot.chillburger.services.categoryService;


import org.springframework.http.HttpEntity;

public interface CategoryService {
    HttpEntity<?> getCategories();
}
