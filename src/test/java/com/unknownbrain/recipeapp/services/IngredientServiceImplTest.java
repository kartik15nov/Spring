package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.commands.IngredientCommand;
import com.unknownbrain.recipeapp.converters.fromCommand.IngredientCommandToIngredient;
import com.unknownbrain.recipeapp.converters.toCommand.IngredientToIngredientCommand;
import com.unknownbrain.recipeapp.converters.toCommand.UnitOfMeasureToUnitOfMeasureCommand;
import com.unknownbrain.recipeapp.models.Ingredient;
import com.unknownbrain.recipeapp.models.Recipe;
import com.unknownbrain.recipeapp.models.UnitOfMeasure;
import com.unknownbrain.recipeapp.repositories.RecipeRepository;
import com.unknownbrain.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        ingredient.setDescription(" hellow");
        ingredient.setAmount(new BigDecimal(1000));
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(34L);
        ingredient.setUom(uom);
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


    @Test
    void deleteById() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        recipe.addIngredient(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //when
        ingredientService.deleteById(1L, 2L);

        //then
        assertEquals(1, recipe.getIngredients().size());
        verify(recipeRepository, atLeastOnce()).findById(anyLong());
        verify(recipeRepository, atLeastOnce()).save(any());
    }
}