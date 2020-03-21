package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.IngredientCommand;
import com.unknownbrain.recipeapp.converters.fromCommand.IngredientCommandToIngredient;
import com.unknownbrain.recipeapp.converters.toCommand.IngredientToIngredientCommand;
import com.unknownbrain.recipeapp.model.Ingredient;
import com.unknownbrain.recipeapp.model.Recipe;
import com.unknownbrain.recipeapp.repositories.RecipeRepository;
import com.unknownbrain.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@Service
public class IngredientServiceImpl implements IngredientService {

    RecipeRepository recipeRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optional = recipeRepository.findById(recipeId);

        AtomicReference<IngredientCommand> ingredientCommand = new AtomicReference<>();

        optional.flatMap(recipe -> recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst())
                .ifPresent(ingredient -> ingredientCommand.getAndSet(ingredientToIngredientCommand.convert(ingredient)));

        return ingredientCommand.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Objects.requireNonNull(ingredientCommand);

        Long recipeId = ingredientCommand.getRecipeId();

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        Recipe savedRecipe = null;
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst()
                    .ifPresent(ingredientFound -> {
                        ingredientFound.setDescription(ingredientCommand.getDescription());
                        ingredientFound.setAmount(ingredientCommand.getAmount());
                        if (ingredientCommand.getUom() != null && ingredientCommand.getUom().getId() != null)
                            ingredientFound.setUom(unitOfMeasureRepository
                                    .findById(ingredientCommand.getUom().getId())
                                    .orElse(null)); //todo address this
                    });
            savedRecipe = recipeRepository.save(recipe);
        }

        assert savedRecipe != null;
        return ingredientToIngredientCommand.convert(
                savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                        .findFirst().orElse(new Ingredient())
        );
    }
}
