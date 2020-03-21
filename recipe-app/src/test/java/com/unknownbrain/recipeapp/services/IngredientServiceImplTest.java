package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.IngredientCommand;
import com.unknownbrain.recipeapp.converters.fromCommand.IngredientCommandToIngredient;
import com.unknownbrain.recipeapp.converters.toCommand.IngredientToIngredientCommand;
import com.unknownbrain.recipeapp.converters.toCommand.UnitOfMeasureToUnitOfMeasureCommand;
import com.unknownbrain.recipeapp.model.Ingredient;
import com.unknownbrain.recipeapp.model.Recipe;
import com.unknownbrain.recipeapp.repositories.RecipeRepository;
import com.unknownbrain.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    void findByRecipeIdAndIngredientId() {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(5L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(6L);
        recipe.addIngredient(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(7L);
        recipe.addIngredient(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(8L);
        recipe.addIngredient(ingredient3);


        when(recipeRepository.findById(anyLong())).thenReturn(java.util.Optional.of(recipe));

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(5L, 7L);

        //then
        assertEquals(7L, ingredientCommand.getId());
        assertEquals(5L, ingredientCommand.getRecipeId());
        verify(recipeRepository).findById(anyLong());
    }

    @Test
    void saveIngredientCommand() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(10L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(12L);
        recipe.addIngredient(ingredient);

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any())).thenReturn(recipe);

        //when
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        //then
        assertEquals(12L, savedIngredientCommand.getId());
        assertEquals(10L, savedIngredientCommand.getRecipeId());
        verify(recipeRepository).save(any(Recipe.class));
    }
}