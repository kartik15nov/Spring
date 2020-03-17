package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.IngredientCommand;
import com.unknownbrain.recipeapp.converters.toCommand.IngredientToIngredientCommand;
import com.unknownbrain.recipeapp.model.Recipe;
import com.unknownbrain.recipeapp.repositories.RecipeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@Service
public class IngredientServiceImpl implements IngredientService {

    RecipeRepository recipeRepository;
    IngredientToIngredientCommand ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optional = recipeRepository.findById(recipeId);

        AtomicReference<IngredientCommand> ingredientCommand = new AtomicReference<>();

        optional.flatMap(recipe -> recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst())
                .ifPresent(ingredient -> ingredientCommand.getAndSet(ingredientCommandToIngredient.convert(ingredient)));

        return ingredientCommand.get();
    }
}
