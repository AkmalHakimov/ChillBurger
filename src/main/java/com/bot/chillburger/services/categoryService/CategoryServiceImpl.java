package com.bot.chillburger.services.categoryService;

import com.bot.chillburger.entity.Category;
import com.bot.chillburger.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public HttpEntity<?> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        return ResponseEntity.ok(categories);
    }
}
