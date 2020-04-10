package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.RecipeCommand;
import com.unknownbrain.recipeapp.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();

    Recipe findById(Long id);

    void deleteById(Long idToDelete);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
