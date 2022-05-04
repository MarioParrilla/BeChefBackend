package com.mp.bechefbackend.bechefbackend.Services;

import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;

import java.util.List;

public interface RecipeService {

    public List<RecipeDTO> findAll();

    public List<RecipeDTO> findRecipesByToken(String token);

}
