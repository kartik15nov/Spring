package com.unknownbrain.recipeapp.repositories;

import com.unknownbrain.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
