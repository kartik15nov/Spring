package com.unknownbrain.recipeapp.repositories;

import com.unknownbrain.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
