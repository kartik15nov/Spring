package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.RecipeCommand;
import com.unknownbrain.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
