package com.unknownbrain.recipeapp.services;

import com.unknownbrain.recipeapp.model.Recipe;
import com.unknownbrain.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {

        List<Recipe> recipeData = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipeData.add(recipe1);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        List<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);

        //To verify the interactions, e.g: to check how many time the findAll() method gets called, then don this..
        //Since in this scenario, the method gets called only once during the testing, so expected is 1 time..
        //Hence if we change the times to 2, then below code will fail since findAll() method never called twice.
        verify(recipeRepository, times(1)).findAll();
    }
}