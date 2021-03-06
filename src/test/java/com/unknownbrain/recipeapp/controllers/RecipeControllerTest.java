package com.unknownbrain.recipeapp.controllers;

import com.unknownbrain.recipeapp.commands.RecipeCommand;
import com.unknownbrain.recipeapp.exceptions.NotFoundException;
import com.unknownbrain.recipeapp.models.Recipe;
import com.unknownbrain.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    RecipeController controller;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();
    }

    @Test
    void getRecipeTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/view"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void getRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/view"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    void getRecipeNotFoundNumberFormatException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/asdf/view"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    void testGETNewRecipeForm() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPOSTNewRecipeForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform
                (post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "something")
                        .param("directions", "Do blah, then do blah blah")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/view"));
    }

    @Test
    void testPOSTNewRecipeFormAndValidationWillFail() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform
                (post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testDeleteAction() throws Exception {
        mockMvc.perform(get("/recipe/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService).deleteById(anyLong());
    }
}